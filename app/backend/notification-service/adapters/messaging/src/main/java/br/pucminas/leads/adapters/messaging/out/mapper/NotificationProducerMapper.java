package br.pucminas.leads.adapters.messaging.out.mapper;

import br.pucminas.leads.application.domain.Notification;
import br.pucminas.leads.schemas.notification_emitted.Channel;
import br.pucminas.leads.schemas.notification_emitted.NotificationEmitted;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationProducerMapper {

    public static NotificationEmitted mapToMessage(Notification notification) {
        if (notification == null) {
            return null;
        }
        var channels = notification.getChannels()
                                   .stream()
                                   .map(channel -> Channel.valueOf(channel.name()))
                                   .collect(Collectors.toList());
        return NotificationEmitted.newBuilder()
                                  .setLeadId(notification.getId())
                                  .setNotifiedAt(notification.getNotifiedAt())
                                  .setChannels(channels)
                                  .build();
    }

}
