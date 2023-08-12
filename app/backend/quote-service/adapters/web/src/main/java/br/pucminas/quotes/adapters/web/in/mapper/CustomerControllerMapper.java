package br.pucminas.quotes.adapters.web.in.mapper;

import br.pucminas.quotes.adapters.web.in.dto.CustomerDto;
import br.pucminas.quotes.adapters.web.in.dto.enums.CustomerTypeEnumDto;
import br.pucminas.quotes.adapters.web.in.dto.enums.GenderEnumDto;
import br.pucminas.quotes.application.domain.Customer;
import br.pucminas.quotes.application.domain.enums.CustomerTypeEnum;
import br.pucminas.quotes.application.domain.enums.GenderEnum;
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
                       .withType(CustomerTypeEnum.fromCode(customerDto.getType().getCode()))
                       .withGender(GenderEnum.fromCode(customerDto.getGender().getCode()))
                       .withDateOfBirth(customerDto.getDateOfBirth())
                       .withEmail(customerDto.getEmail())
                       .withPhoneNumber(customerDto.getPhoneNumber())
                       .build();
    }

    public static CustomerDto mapToDto(Customer customer) {
        if (customer == null) {
            return null;
        }
        return CustomerDto.builder()
                          .withDocumentNumber(customer.getDocumentNumber())
                          .withName(customer.getName())
                          .withType(CustomerTypeEnumDto.fromCode(customer.getType().getCode()))
                          .withGender(GenderEnumDto.fromCode(customer.getGender().getCode()))
                          .withDateOfBirth(customer.getDateOfBirth())
                          .withEmail(customer.getEmail())
                          .withPhoneNumber(customer.getPhoneNumber())
                          .build();
    }
}
