package br.pucminas.leads.adapters.email;

import br.pucminas.leads.adapters.email.mapper.EmailAdapterMapper;
import br.pucminas.leads.application.domain.Notification;
import br.pucminas.leads.application.ports.out.SendNotificationPort;
import br.pucminas.leads.common.EmailAdapter;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static net.logstash.logback.marker.Markers.append;

@EmailAdapter
@RequiredArgsConstructor
@Slf4j
public class SendNotificationEmailAdapter implements SendNotificationPort {

    private final AmazonSimpleEmailService emailService;

    @Override
    public void send(Notification notification) {
        log.info(append("notification", notification), "Mapping notification");
        var emailRequest = EmailAdapterMapper.mapToMessage(notification);
        log.info(append("email_request", emailRequest), "Notification was mapped successfully");

        log.info(append("email_request", emailRequest), "Sending e-mail");
        emailService.sendTemplatedEmail(emailRequest);
        log.info(append("email_request", emailRequest), "E-mail was sent successfully");
    }

}
