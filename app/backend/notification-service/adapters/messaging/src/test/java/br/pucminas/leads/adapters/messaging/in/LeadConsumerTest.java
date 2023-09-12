package br.pucminas.leads.adapters.messaging.in;

import br.pucminas.leads.adapters.messaging.config.TopicProperties;
import br.pucminas.leads.application.domain.Notification;
import br.pucminas.leads.application.ports.in.SendNotificationUseCase;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static br.pucminas.leads.adapters.messaging.in.support.LeadProcessedMessageSupport.defaultLeadProcessedMessage;
import static org.awaitility.Awaitility.waitAtMost;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ActiveProfiles("test")
@Import({ KafkaAutoConfiguration.class, LeadConsumer.class })
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@EmbeddedKafka(partitions = 1, controlledShutdown = true)
class LeadConsumerTest {

    private final EmbeddedKafkaBroker embeddedKafkaBroker;
    private final TopicProperties topicProperties;

    @MockBean
    private SendNotificationUseCase useCase;

    private AutoCloseable closeable;
    private String topic;

    @BeforeEach
    public void setup(){
        this.closeable = MockitoAnnotations.openMocks(this);
        this.topic = this.topicProperties.getInputTopic(TopicProperties.LEAD_PROCESSED);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Given Message When Receive Then Send Notification")
    public void givenMessageWhenReceiveThenSendNotification() {
        var message = defaultLeadProcessedMessage().build();
        Producer<String, SpecificRecord> producer = createProducer();

        producer.send(new ProducerRecord<>(topic, UUID.randomUUID().toString(), message));
        producer.flush();
        producer.close();

        waitAtMost(20, TimeUnit.SECONDS).untilAsserted(() -> {
            verify(useCase, times(1)).send(any(Notification.class));
        });
    }

    private Producer<String, SpecificRecord> createProducer() {
        Map<String, Object> producerProps = KafkaTestUtils.producerProps(embeddedKafkaBroker);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        producerProps.put(KafkaAvroSerializerConfig.AUTO_REGISTER_SCHEMAS, "true");
        producerProps.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "mock://http://localhost:8081");
        return new DefaultKafkaProducerFactory<String, SpecificRecord>(producerProps).createProducer();
    }



}