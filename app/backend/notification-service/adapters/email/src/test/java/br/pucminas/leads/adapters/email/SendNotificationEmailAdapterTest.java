package br.pucminas.leads.adapters.email;

import br.pucminas.leads.adapters.email.config.ObjectMapperConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static br.pucminas.leads.application.support.NotificationSupport.defaultNotification;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(SpringExtension.class)
@Import({MailSenderAutoConfiguration.class, SendNotificationEmailAdapter.class, ObjectMapperConfiguration.class})
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@ActiveProfiles("test")
class SendNotificationEmailAdapterTest {

    @InjectMocks
    private SendNotificationEmailAdapter adapter;

    @MockBean
    private JavaMailSender mailSender;

    @Autowired
    private ObjectMapper objectMapper;

    private AutoCloseable autoCloseable;

    @BeforeEach
    public void setup() {
        this.autoCloseable = MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(this.adapter, "mailSender", mailSender);
        ReflectionTestUtils.setField(this.adapter, "objectMapper", objectMapper);
    }

    @AfterEach
    public void tearDown() throws Exception {
        this.autoCloseable.close();
    }

    @Test
    @DisplayName("Given Notification When Is Valid Then Send Email")
    public void givenNotificationWhenIsValidThenSendEmail() {
        var notification = defaultNotification().build();
        this.adapter.send(notification);
        verify(this.mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

}