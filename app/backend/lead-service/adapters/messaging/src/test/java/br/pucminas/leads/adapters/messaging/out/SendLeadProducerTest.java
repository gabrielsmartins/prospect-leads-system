package br.pucminas.leads.adapters.messaging.out;

import br.pucminas.leads.adapters.messaging.config.TopicProperties;

import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Map;

import static br.pucminas.leads.application.support.LeadSupport.defaultLead;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@EmbeddedKafka(partitions = 1, controlledShutdown = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@EnableConfigurationProperties(TopicProperties.class)
@Import({KafkaAutoConfiguration.class, SendLeadProducer.class})
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@ActiveProfiles("test")
class SendLeadProducerTest {

    private final EmbeddedKafkaBroker embeddedKafkaBroker;
    private final TopicProperties topicProperties;
    private final SendLeadProducer adapter;

    private String topic;

    @BeforeEach
    public void setup() {
        this.topic = this.topicProperties.getOutputTopic(TopicProperties.LEAD_PROCESSED);
    }


    @Test
    @DisplayName("Given Message When Send Then Call Template")
    public void givenMessageWhenSendThenCallTemplate() {
        var lead = defaultLead().build();
        this.adapter.send(lead);

        var consumer = createConsumer();

        var singleRecord = KafkaTestUtils.getSingleRecord(consumer, topic);

        assertThat(singleRecord).isNotNull();
        assertThat(singleRecord.key()).isEqualTo(lead.getInsuranceQuote().getId().toString());
        assertThat(singleRecord.value()).isNotNull();
    }

    private Consumer<String, SpecificRecord> createConsumer() {
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(SendLeadProducerTest.class.getSimpleName(), "true", embeddedKafkaBroker);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        consumerProps.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");
        consumerProps.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "mock://http://localhost:8081");
        Consumer<String, SpecificRecord> consumer = new DefaultKafkaConsumerFactory<String, SpecificRecord>(consumerProps).createConsumer();
        consumer.subscribe(Collections.singleton(topic));
        return consumer;
    }


}