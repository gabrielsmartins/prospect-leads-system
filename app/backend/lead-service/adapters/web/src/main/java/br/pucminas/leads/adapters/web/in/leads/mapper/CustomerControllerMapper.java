package br.pucminas.leads.adapters.web.in.leads.mapper;

import br.pucminas.leads.adapters.web.in.leads.dto.CustomerDto;
import br.pucminas.leads.application.domain.Customer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerControllerMapper {

    public static CustomerDto mapToDto(Customer customer) {
        if (customer == null) {
            return null;
        }
        return CustomerDto.builder()
                        .withDocumentNumber(customer.getDocumentNumber())
                        .withType(customer.getType())
                        .withGender(customer.getGender())
                        .withName(customer.getName())
                        .withDateOfBirth(customer.getDateOfBirth())
                        .withEmail(customer.getEmail())
                        .withPhoneNumber(customer.getPhoneNumber())
                        .build();
    }

}
