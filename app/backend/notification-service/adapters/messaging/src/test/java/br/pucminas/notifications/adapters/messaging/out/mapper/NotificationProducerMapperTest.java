package br.pucminas.notifications.adapters.messaging.out.mapper;


import br.pucminas.notifications.adapters.messaging.out.mapper.NotificationProducerMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.pucminas.notifications.application.support.NotificationSupport.defaultNotification;
import static org.assertj.core.api.Assertions.assertThat;

class NotificationProducerMapperTest {


    @Test
    @DisplayName("Given Notification When Map Then Return Notification Message")
    public void givenNotificationWhenMapThenReturnNotificationMessage() {
        var notification = defaultNotification().build();
        var message = NotificationProducerMapper.mapToMessage(notification);

        assertThat(message).isNotNull();
        assertThat(message).hasNoNullFieldsOrProperties();
        assertThat(message.getLeadId()).isEqualTo(notification.getId());
        assertThat(message.getNotifiedAt()).isEqualTo(notification.getNotifiedAt());
        assertThat(message.getChannels().size()).isEqualTo(notification.getChannels().size());
    }

}