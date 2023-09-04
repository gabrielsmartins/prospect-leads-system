package br.pucminas.leads.application.service;

import br.pucminas.leads.application.domain.Notification;
import br.pucminas.leads.application.ports.out.SendNotificationPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static br.pucminas.leads.application.support.NotificationSupport.defaultNotification;
import static org.mockito.Mockito.*;

class SendNotificationServiceTest {

    private SendNotificationService service;
    private List<SendNotificationPort> ports;

    @BeforeEach
    public void setup() {
        this.ports = new LinkedList<>();
        this.service = new SendNotificationService(this.ports);
    }

    @Test
    @DisplayName("Given Notification When Send Then Call All Ports")
    public void givenNotificationWhenSendThenCallAllPorts() {
        var mockPortOne = mock(SendNotificationPort.class);
        this.ports.add(mockPortOne);

        var mockPortTwo = mock(SendNotificationPort.class);
        this.ports.add(mockPortTwo);

        var notification = defaultNotification().build();
        this.service.send(notification);
        verify(mockPortOne, times(1)).send(notification);
        verify(mockPortTwo, times(1)).send(notification);
    }

}