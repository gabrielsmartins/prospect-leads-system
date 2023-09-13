package br.pucminas.leads.adapters.email;

import br.pucminas.leads.adapters.email.dto.EmailDto;
import br.pucminas.leads.adapters.email.mapper.EmailAdapterMapper;
import br.pucminas.leads.application.domain.Notification;
import br.pucminas.leads.application.domain.enums.ChannelEnum;
import br.pucminas.leads.application.ports.out.SendNotificationPort;
import br.pucminas.leads.common.EmailAdapter;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

import static br.pucminas.leads.adapters.email.config.AwsSesTemplateConfiguration.TEMPLATE;
import static net.logstash.logback.marker.Markers.append;

@EmailAdapter
@RequiredArgsConstructor
@Slf4j
public class SendNotificationEmailAdapter implements SendNotificationPort {

    private final AmazonSimpleEmailService emailService;
    private final ObjectMapper objectMapper;

    @Override
    public void send(Notification notification) {
        log.info(append("notification", notification), "Mapping notification");
        var emailDto = EmailAdapterMapper.mapToEmail(notification);
        log.info(append("email", emailDto), "Notification was mapped successfully");

        var emailRequest = new SendTemplatedEmailRequest();
        emailRequest.setTemplate(TEMPLATE);
        emailRequest.setSource(emailDto.getFromEmail());
        emailRequest.setDestination(new Destination(Collections.singletonList(emailDto.getToEmail())));
        emailRequest.setTemplateData(this.toTemplateData(emailDto));

        log.info(append("email_request", emailRequest), "Sending e-mail");
        emailService.sendTemplatedEmail(emailRequest);
        log.info(append("email_request", emailRequest), "E-mail was sent successfully");
    }

    @SneakyThrows
    private String toTemplateData(EmailDto emailDto) {
        return this.objectMapper.writeValueAsString(emailDto);
    }

    @Override
    public ChannelEnum getChannel() {
        return ChannelEnum.E_MAIL;
    }

}
