package br.pucminas.leads.adapters.messaging.support;

import br.pucminas.leads.schemas.notification_emitted.Channel;
import br.pucminas.leads.schemas.notification_emitted.NotificationEmitted;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationMessageSupport {

    public static NotificationEmitted.Builder defaultNotificationEmittedMessage() {
        return NotificationEmitted.newBuilder()
                .setLeadId(UUID.randomUUID())
                .setNotifiedAt(LocalDateTime.now())
                .setChannels(List.of(Channel.values()));
    }
}
