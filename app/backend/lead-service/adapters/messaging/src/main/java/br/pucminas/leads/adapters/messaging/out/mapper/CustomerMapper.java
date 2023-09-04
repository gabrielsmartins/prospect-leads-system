package br.pucminas.leads.adapters.messaging.out.mapper;

import br.pucminas.notifications.schemas.lead_processed.Customer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerMapper {

    public static Customer mapToMessage(br.pucminas.leads.application.domain.Customer customer) {

    }

}
