package br.pucminas.leads.adapters.messaging.out;

import br.pucminas.leads.adapters.messaging.config.TopicProperties;
import br.pucminas.leads.adapters.messaging.out.mapper.NotificationProducerMapper;
import br.pucminas.leads.application.domain.Notification;
import br.pucminas.leads.application.ports.out.ConfirmNotificationPort;
import br.pucminas.leads.common.MessagingAdapter;
import br.pucminas.leads.schemas.notification_emitted.NotificationEmitted;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;

import static net.logstash.logback.marker.Markers.append;

@MessagingAdapter
@RequiredArgsConstructor
@EnableConfigurationProperties(TopicProperties.class)
@Slf4j
public class ConfirmNotificationProducer implements ConfirmNotificationPort {

    private final KafkaTemplate<String, NotificationEmitted> kafkaTemplate;
    private final TopicProperties properties;

    @Override
    public void confirm(Notification notification) {
        log.info(append("notification", notification), "Mapping notification");
        var notificationEmitted = NotificationProducerMapper.mapToMessage(notification);
        log.info(append("notification", notificationEmitted), "Notification was mapped successfully");

        var topic = this.properties.getOutputTopic(TopicProperties.NOTIFICATION_EMITTED);

        var message = MessageBuilder.withPayload(notificationEmitted)
                                    .setHeader(KafkaHeaders.TOPIC, topic)
                                    .setHeader(KafkaHeaders.MESSAGE_KEY, notificationEmitted.getLeadId().toString())
                                    .build();

        log.info(append("payload", message), "Sending notification");
        this.kafkaTemplate.send(message)
                          .addCallback(result -> log.info(append("result", result), "Message was sent successfully"),
                                       ex -> log.error("Error sending message", ex));
    }

}
