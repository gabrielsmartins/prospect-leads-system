package br.pucminas.bff.adapters.web.in.support;


import br.pucminas.bff.adapters.web.in.quotes.dto.CustomerDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerDtoSupport {

    public static CustomerDto.CustomerDtoBuilder defaultCustomerDto() {
        return CustomerDto.builder()
                       .withName("John Wick")
                       .withDocumentNumber("00000100000")
                       .withType("F")
                       .withGender("M")
                       .withDateOfBirth(LocalDate.now().minusYears(30));
    }
}
