package br.pucminas.notifications.application.ports.out;

import br.pucminas.notifications.application.domain.Notification;

public interface ConfirmNotificationPort {

    void confirm(Notification notification);

}
