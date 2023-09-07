package br.pucminas.leads.adapters.messaging.out.mapper;

import br.pucminas.notifications.schemas.lead_processed.Customer;
import br.pucminas.notifications.schemas.lead_processed.CustomerGender;
import br.pucminas.notifications.schemas.lead_processed.CustomerType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerMapper {

    public static Customer mapToMessage(br.pucminas.leads.application.domain.Customer customer) {
        if (customer == null) {
            return null;
        }
        return Customer.newBuilder()
                       .setDocumentNumber(customer.getDocumentNumber())
                       .setName(customer.getName())
                       .setType(CustomerType.valueOf(customer.getType()))
                       .setGender(CustomerGender.valueOf(customer.getGender()))
                       .setDateOfBirth(customer.getDateOfBirth())
                       .setPhoneNumber(customer.getPhoneNumber())
                       .setEmail(customer.getEmail())
                       .build();
    }

}
