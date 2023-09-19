package br.pucminas.leads.adapters.messaging.in;

import br.pucminas.leads.application.ports.in.FinishLeadUseCase;
import br.pucminas.leads.common.stereotype.MessagingAdapter;
import br.pucminas.leads.schemas.notification_emitted.NotificationEmitted;
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
@KafkaListener(topics = "${topic.input.notification-emitted}")
@Slf4j
public class NotificationConsumer {

    private final FinishLeadUseCase useCase;

    @KafkaHandler
    public void consume(@Headers MessageHeaders headers, @Payload NotificationEmitted message, Acknowledgment acknowledgment) {
        try {
            log.info(append("headers", headers).and(append("payload", message)), "Receiving processed lead");
            var id = message.getInsuranceQuoteId();
            log.info(append("id", id), "Finishing lead");
            this.useCase.finish(id);
            log.info(append("notification", id), "Lead was finished successfully");
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
