package br.pucminas.bff.adapters.web.out.quotes.mapper;

import br.pucminas.bff.adapters.web.out.quotes.dto.CustomerDto;
import br.pucminas.bff.application.domain.Customer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerWebAdapterMapper {

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

    public static Customer mapToDomain(CustomerDto customerDto) {
        if (customerDto == null) {
            return null;
        }
        return Customer.builder()
                       .withDocumentNumber(customerDto.getDocumentNumber())
                       .withType(customerDto.getType())
                       .withGender(customerDto.getGender())
                       .withName(customerDto.getName())
                       .withDateOfBirth(customerDto.getDateOfBirth())
                       .withEmail(customerDto.getEmail())
                       .withPhoneNumber(customerDto.getPhoneNumber())
                       .build();
    }
}
