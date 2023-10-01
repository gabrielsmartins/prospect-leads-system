package br.pucminas.notifications.application.ports.in;

import br.pucminas.notifications.application.domain.Notification;

public interface SendNotificationUseCase {

    void send(Notification notification);

}
