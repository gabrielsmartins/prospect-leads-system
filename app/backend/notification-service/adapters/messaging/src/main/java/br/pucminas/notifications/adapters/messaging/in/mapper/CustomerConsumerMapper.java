package br.pucminas.notifications.adapters.messaging.in.mapper;

import br.pucminas.notifications.application.domain.Customer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerConsumerMapper {

    public static Customer mapToDomain(br.pucminas.leads.schemas.lead_processed.Customer customerMessage) {
        if (customerMessage == null) {
            return null;
        }
        return Customer.builder()
                        .withName(customerMessage.getName())
                        .withType(customerMessage.getType().name())
                        .withDocumentNumber(customerMessage.getDocumentNumber())
                        .withGender(customerMessage.getGender().name())
                        .withDateOfBirth(customerMessage.getDateOfBirth())
                        .withPhoneNumber(customerMessage.getPhoneNumber())
                        .withEmail(customerMessage.getEmail())
                        .build();
    }
}
