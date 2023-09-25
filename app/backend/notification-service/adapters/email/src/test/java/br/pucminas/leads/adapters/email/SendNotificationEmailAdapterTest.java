package br.pucminas.leads.adapters.email;

import br.pucminas.leads.adapters.email.config.AwsSesConfiguration;
import br.pucminas.leads.adapters.email.config.AwsSesTemplateConfiguration;
import br.pucminas.leads.adapters.email.support.LocalstackSupport;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.pucminas.leads.application.support.NotificationSupport.defaultNotification;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(SpringExtension.class)
@Import({AwsSesConfiguration.class, AwsSesTemplateConfiguration.class})
class SendNotificationEmailAdapterTest extends LocalstackSupport {

    @InjectMocks
    private SendNotificationEmailAdapter adapter;

    @SpyBean
    private AmazonSimpleEmailService emailService;

    @Test
    @DisplayName("Given Notification When Is Valid Then Send Email")
    public void givenNotificationWhenIsValidThenSendEmail() {
        var notification = defaultNotification().build();
        this.adapter.send(notification);
        verify(this.emailService, times(1)).sendTemplatedEmail(any(SendTemplatedEmailRequest.class));
    }

}