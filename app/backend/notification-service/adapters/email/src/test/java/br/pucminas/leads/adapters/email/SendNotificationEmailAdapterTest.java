package br.pucminas.leads.adapters.email;

import br.pucminas.leads.adapters.email.config.AwsSesConfiguration;
import br.pucminas.leads.adapters.email.config.AwsSesTemplateConfiguration;
import br.pucminas.leads.adapters.email.config.ObjectMapperConfiguration;
import br.pucminas.leads.adapters.email.support.LocalstackSupport;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest;
import com.amazonaws.services.simpleemail.model.VerifyEmailAddressRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static br.pucminas.leads.adapters.email.mapper.EmailAdapterMapper.FROM_EMAIL;
import static br.pucminas.leads.application.support.NotificationSupport.defaultNotification;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(SpringExtension.class)
@Import({AwsSesConfiguration.class, AwsSesTemplateConfiguration.class, ObjectMapperConfiguration.class})
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@ActiveProfiles("test")
class SendNotificationEmailAdapterTest extends LocalstackSupport {

    @InjectMocks
    private SendNotificationEmailAdapter adapter;

    @SpyBean
    private AmazonSimpleEmailService emailService;

    @Autowired
    private ObjectMapper objectMapper;

    private AutoCloseable autoCloseable;

    @BeforeEach
    public void setup() {
        this.autoCloseable = MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(this.adapter, "emailService", emailService);
        ReflectionTestUtils.setField(this.adapter, "objectMapper", objectMapper);
        this.emailService.verifyEmailAddress(new VerifyEmailAddressRequest().withEmailAddress(FROM_EMAIL));
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
        verify(this.emailService, times(1)).sendTemplatedEmail(any(SendTemplatedEmailRequest.class));
    }

}