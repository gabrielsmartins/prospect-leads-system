package br.pucminas.notifications.application.service;

import br.pucminas.notifications.application.domain.Notification;
import br.pucminas.notifications.application.ports.in.SendNotificationUseCase;
import br.pucminas.notifications.application.ports.out.ConfirmNotificationPort;
import br.pucminas.notifications.application.ports.out.SendNotificationPort;
import br.pucminas.notifications.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class SendNotificationService implements SendNotificationUseCase {

    private final List<SendNotificationPort> sendNotificationPorts;
    private final ConfirmNotificationPort confirmNotificationPort;

    @Override
    public void send(Notification notification) {
        this.sendNotificationPorts.forEach(port -> {
            notification.addChannel(port.getChannel());
            notification.setNotifiedAt(LocalDateTime.now());
            port.send(notification);
        });
        this.confirmNotificationPort.confirm(notification);
    }

}
