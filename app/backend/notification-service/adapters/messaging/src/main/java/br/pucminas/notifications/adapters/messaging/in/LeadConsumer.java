package br.pucminas.notifications.adapters.messaging.in;

import br.pucminas.notifications.adapters.messaging.in.mapper.LeadConsumerMapper;
import br.pucminas.notifications.application.ports.in.SendNotificationUseCase;
import br.pucminas.notifications.common.stereotype.MessagingAdapter;
import br.pucminas.leads.schemas.lead_processed.LeadProcessed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import static net.logstash.logback.marker.Markers.append;

@MessagingAdapter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@KafkaListener(topics = "${topic.input.lead-processed}")
@Slf4j
public class LeadConsumer {

    private final SendNotificationUseCase useCase;

    @KafkaHandler
    public void consume(@Headers MessageHeaders headers, @Payload LeadProcessed message, Acknowledgment acknowledgment) {
        try {
            log.info(append("headers", headers).and(append("payload", message)), "Receiving processed lead");

            log.info(append("payload", message), "Mapping processed lead");
            var notification = LeadConsumerMapper.mapToDomain(message);
            log.info(append("notification", notification), "Processed lead was mapped successfully");

            log.info(append("notification", notification), "Sending notification");
            this.useCase.send(notification);
            log.info(append("notification", notification), "Notification was sent successfully");
        } catch (Exception ex) {
            log.error("Error while consuming message", ex);
        } finally {
            acknowledgment.acknowledge();
        }
    }

    @KafkaHandler(isDefault = true)
    public void consume(Object object, Acknowledgment acknowledgment) {
        log.warn(append("payload", object), "Unrecognized message");
        acknowledgment.acknowledge();
    }

}