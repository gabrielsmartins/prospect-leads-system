package br.pucminas.notifications.application.ports.out;

import br.pucminas.notifications.application.domain.Notification;
import br.pucminas.notifications.application.domain.enums.ChannelEnum;

public interface SendNotificationPort {

    void send(Notification notification);

    ChannelEnum getChannel();

}
