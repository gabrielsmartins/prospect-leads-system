package br.pucminas.leads.application.ports.out;

import br.pucminas.leads.application.domain.Notification;

public interface ConfirmNotificationPort {

    void confirm(Notification notification);

}
