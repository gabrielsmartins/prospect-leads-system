package br.pucminas.leads.application.support;

import br.pucminas.leads.application.domain.Notification;
import br.pucminas.leads.application.domain.enums.ChannelEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static br.pucminas.leads.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationSupport {

    public static Notification.NotificationBuilder defaultNotification() {
        return Notification.builder()
                .withInsuranceQuote(defaultInsuranceQuote().build())
                .withCreatedAt(LocalDateTime.now())
                .withProcessedAt(LocalDateTime.now())
                .withNotifiedAt(LocalDateTime.now())
                .withChannels(new ArrayList<>(List.of(ChannelEnum.values())));
    }

}
