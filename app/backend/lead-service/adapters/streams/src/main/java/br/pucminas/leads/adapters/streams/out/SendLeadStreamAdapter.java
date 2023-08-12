package br.pucminas.leads.adapters.streams.out;

import br.pucminas.leads.adapters.streams.out.mapper.LeadStreamAdapterMapper;
import br.pucminas.leads.application.domain.Lead;
import br.pucminas.leads.application.ports.out.SendLeadPort;
import br.pucminas.leads.common.SteamAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;

import static net.logstash.logback.marker.Markers.append;

@SteamAdapter
@RequiredArgsConstructor
@Slf4j
public class SendLeadStreamAdapter implements SendLeadPort {

    private final KafkaTemplate<String, SpecificRecord> kafkaTemplate;

    @Override
    public void send(Lead lead) {
        log.info(append("lead", lead), "Mapping lead");
        var leadProcessed = LeadStreamAdapterMapper.mapToMessage(lead);
        log.info(append("lead", leadProcessed), "Lead was mapped successfully");
        var message = MessageBuilder.withPayload(leadProcessed)
                                    .setHeader(KafkaHeaders.MESSAGE_KEY, lead.getInsuranceQuoteId())
                                    .build();
        log.info(append("message", message), "Sending message");
        kafkaTemplate.send(message)
                     .addCallback(result -> log.info(append("result", result), "Message was sent successfully"),
                                  ex -> log.error("Error sending message", ex));
    }
}
