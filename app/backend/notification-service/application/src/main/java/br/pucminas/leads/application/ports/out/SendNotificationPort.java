package br.pucminas.leads.application.ports.out;

import br.pucminas.leads.application.domain.Notification;
import br.pucminas.leads.application.domain.enums.ChannelEnum;

public interface SendNotificationPort {

    void send(Notification notification);

    ChannelEnum getChannel();

}
