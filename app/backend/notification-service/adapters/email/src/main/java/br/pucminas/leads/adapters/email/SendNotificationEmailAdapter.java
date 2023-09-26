package br.pucminas.leads.adapters.email;

import br.pucminas.leads.adapters.email.dto.EmailDto;
import br.pucminas.leads.adapters.email.mapper.EmailAdapterMapper;
import br.pucminas.leads.application.domain.Notification;
import br.pucminas.leads.application.domain.enums.ChannelEnum;
import br.pucminas.leads.application.ports.out.SendNotificationPort;
import br.pucminas.leads.common.EmailAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static net.logstash.logback.marker.Markers.append;

@EmailAdapter
@RequiredArgsConstructor
@Slf4j
public class SendNotificationEmailAdapter implements SendNotificationPort {

    private final JavaMailSender mailSender;
    private final ObjectMapper objectMapper;

    @Override
    public void send(Notification notification) {
        log.info(append("notification", notification), "Mapping notification");
        var emailDto = EmailAdapterMapper.mapToEmail(notification);
        log.info(append("email", emailDto), "Notification was mapped successfully");

        var message = new SimpleMailMessage();
        message.setFrom(emailDto.getFromEmail());
        message.setTo(emailDto.getToEmail());
        message.setSubject(String.format("Olá %s", emailDto.getCustomerName()));
        message.setText(this.toJson(emailDto));

        log.info(append("email", message), "Sending e-mail");
        this.mailSender.send(message);
        log.info(append("email", message), "E-mail was sent successfully");
    }

    @SneakyThrows
    private String toJson(EmailDto emailDto) {
        return this.objectMapper.writeValueAsString(emailDto);
    }

    @Override
    public ChannelEnum getChannel() {
        return ChannelEnum.E_MAIL;
    }

}
