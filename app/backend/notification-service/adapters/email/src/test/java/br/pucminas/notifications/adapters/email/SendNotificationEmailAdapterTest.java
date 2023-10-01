package br.pucminas.notifications.adapters.email;

import br.pucminas.notifications.adapters.email.SendNotificationEmailAdapter;
import br.pucminas.notifications.adapters.email.config.EmailConfiguration;
import br.pucminas.notifications.adapters.email.config.ObjectMapperConfiguration;
import com.dumbster.smtp.SimpleSmtpServer;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static br.pucminas.notifications.application.support.NotificationSupport.defaultNotification;


@ExtendWith(SpringExtension.class)
@Import({MailSenderAutoConfiguration.class, EmailConfiguration.class, SendNotificationEmailAdapter.class, ObjectMapperConfiguration.class})
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ActiveProfiles("test")
class SendNotificationEmailAdapterTest {


    private final SendNotificationEmailAdapter adapter;
    private SimpleSmtpServer dumbster;

    @BeforeEach
    public void setup() throws IOException {
        this.dumbster = SimpleSmtpServer.start(5005);
    }

    @AfterEach
    public void tearDown() throws IOException {
        this.dumbster.close();
    }

    @Test
    @DisplayName("Given Notification When Is Valid Then Send Email")
    public void givenNotificationWhenIsValidThenSendEmail() {
        var notification = defaultNotification().build();
        this.adapter.send(notification);
    }

}