package br.pucminas.leads.application.ports.out;

import br.pucminas.leads.application.domain.Notification;

public interface SendNotificationPort {

    void send(Notification notification);

}
