package br.pucminas.bff.adapters.web.in.quotes.mapper;


import br.pucminas.bff.adapters.web.in.quotes.dto.CustomerDto;
import br.pucminas.bff.application.domain.Customer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerControllerMapper {

    public static Customer mapToDomain(CustomerDto customerDto) {
        if (customerDto == null) {
            return null;
        }
        return Customer.builder()
                       .withDocumentNumber(customerDto.getDocumentNumber())
                       .withName(customerDto.getName())
                       .withType(customerDto.getType())
                       .withGender(customerDto.getGender())
                       .withDateOfBirth(customerDto.getDateOfBirth())
                       .build();
    }

    public static CustomerDto mapToDto(Customer customer) {
        if (customer == null) {
            return null;
        }
        return CustomerDto.builder()
                          .withDocumentNumber(customer.getDocumentNumber())
                          .withName(customer.getName())
                          .withType(customer.getType())
                          .withGender(customer.getGender())
                          .withDateOfBirth(customer.getDateOfBirth())
                          .build();
    }
}
