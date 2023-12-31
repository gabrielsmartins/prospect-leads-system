package br.pucminas.notifications.application.support;

import br.pucminas.notifications.application.domain.Notification;
import br.pucminas.notifications.application.domain.enums.ChannelEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static br.pucminas.notifications.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationSupport {

    public static Notification.NotificationBuilder defaultNotification() {
        return Notification.builder()
                .withId(UUID.randomUUID())
                .withInsuranceQuote(defaultInsuranceQuote().build())
                .withCreatedAt(LocalDateTime.now())
                .withProcessedAt(LocalDateTime.now())
                .withNotifiedAt(LocalDateTime.now())
                .withChannels(new ArrayList<>(List.of(ChannelEnum.values())));
    }

}
