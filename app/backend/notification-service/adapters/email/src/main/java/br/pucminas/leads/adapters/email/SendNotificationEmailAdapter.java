package br.pucminas.leads.adapters.email;

import br.pucminas.leads.adapters.email.dto.EmailDto;
import br.pucminas.leads.adapters.email.mapper.EmailAdapterMapper;
import br.pucminas.leads.application.domain.Notification;
import br.pucminas.leads.application.domain.enums.ChannelEnum;
import br.pucminas.leads.application.ports.out.SendNotificationPort;
import br.pucminas.leads.common.EmailAdapter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static net.logstash.logback.marker.Markers.append;

@EmailAdapter
@RequiredArgsConstructor
@Slf4j
public class SendNotificationEmailAdapter implements SendNotificationPort {

    private final JavaMailSender mailSender;
    private final ObjectMapper objectMapper;
    private final SpringTemplateEngine templateEngine;

    @Override
    @SneakyThrows
    public void send(Notification notification) {
        log.info(append("notification", notification), "Mapping notification");
        var emailDto = EmailAdapterMapper.mapToEmail(notification);
        log.info(append("email", emailDto), "Notification was mapped successfully");

        var message = mailSender.createMimeMessage();
        var helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        var context = new Context();
        context.setVariables(this.toMap(emailDto));

        var html = templateEngine.process("offer", context);
        helper.setFrom(emailDto.getFromEmail());
        helper.setTo(emailDto.getToEmail());
        helper.setText(html, true);
        helper.setSubject(String.format("Olá %s", emailDto.getCustomerName()));

        log.info(append("email", message), "Sending e-mail");
        this.mailSender.send(message);
        log.info(append("email", message), "E-mail was sent successfully");
    }

    @SneakyThrows
    private Map<String, Object> toMap(EmailDto emailDto) {
        var json = this.toJson(emailDto);
        var typeRef = new TypeReference<HashMap<String, Object>>() {};
        return objectMapper.readValue(json, typeRef);
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
