package br.pucminas.leads.application.ports.in;

import br.pucminas.leads.application.domain.Notification;

public interface SendNotificationUseCase {

    void send(Notification notification);

}
