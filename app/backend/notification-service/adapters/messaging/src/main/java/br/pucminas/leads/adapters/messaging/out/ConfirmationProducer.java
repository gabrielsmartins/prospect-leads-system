package br.pucminas.leads.adapters.messaging.out;

import br.pucminas.leads.application.domain.Notification;
import br.pucminas.leads.application.ports.out.ConfirmNotificationPort;
import br.pucminas.leads.common.MessagingAdapter;
import lombok.RequiredArgsConstructor;

@MessagingAdapter
@RequiredArgsConstructor
public class ConfirmationProducer implements ConfirmNotificationPort {


    @Override
    public void confirm(Notification notification) {

    }

}
