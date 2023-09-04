package br.pucminas.leads.adapters.messaging.out;

import br.pucminas.leads.adapters.messaging.config.TopicProperties;
import br.pucminas.leads.adapters.messaging.out.mapper.LeadProducerMapper;
import br.pucminas.leads.application.domain.Lead;
import br.pucminas.leads.application.ports.out.SendLeadPort;
import br.pucminas.leads.common.stereotype.MessagingAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;

import static net.logstash.logback.marker.Markers.append;

@MessagingAdapter
@RequiredArgsConstructor
@Slf4j
@EnableConfigurationProperties(TopicProperties.class)
public class SendLeadProducer implements SendLeadPort {

    private final KafkaTemplate<String, SpecificRecord> kafkaTemplate;
    private final TopicProperties topicProperties;

    @Override
    public void send(Lead lead) {
        log.info(append("lead", lead), "Mapping lead");
        var leadProcessed = LeadProducerMapper.mapToMessage(lead);
        log.info(append("lead", leadProcessed), "Lead was mapped successfully");
        var topic = this.topicProperties.getOutputTopic(TopicProperties.LEAD_PROCESSED);
        var message = MessageBuilder.withPayload(leadProcessed)
                                    .setHeader(KafkaHeaders.TOPIC, topic)
                                    .setHeader(KafkaHeaders.MESSAGE_KEY, lead.getInsuranceQuoteId().toString())
                                    .build();
        log.info(append("message", message), "Sending message");
        kafkaTemplate.send(message)
                     .addCallback(result -> log.info(append("result", result), "Message was sent successfully"),
                                  ex -> log.error("Error sending message", ex));
    }
}
