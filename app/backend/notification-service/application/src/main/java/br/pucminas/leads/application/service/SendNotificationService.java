package br.pucminas.leads.application.service;

import br.pucminas.leads.application.domain.Notification;
import br.pucminas.leads.application.ports.in.SendNotificationUseCase;
import br.pucminas.leads.application.ports.out.ConfirmNotificationPort;
import br.pucminas.leads.application.ports.out.SendNotificationPort;
import br.pucminas.leads.common.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class SendNotificationService implements SendNotificationUseCase {

    private final List<SendNotificationPort> sendNotificationPorts;
    private final ConfirmNotificationPort confirmNotificationPort;

    @Override
    public void send(Notification notification) {
        this.sendNotificationPorts.forEach(port -> port.send(notification));
        this.confirmNotificationPort.confirm(notification);
    }

}
