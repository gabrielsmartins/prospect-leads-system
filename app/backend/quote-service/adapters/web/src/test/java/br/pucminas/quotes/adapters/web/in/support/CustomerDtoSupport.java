package br.pucminas.quotes.adapters.web.in.support;

import br.pucminas.quotes.adapters.web.in.dto.CustomerDto;
import br.pucminas.quotes.adapters.web.in.dto.enums.CustomerTypeEnumDto;
import br.pucminas.quotes.adapters.web.in.dto.enums.GenderEnumDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerDtoSupport {

    public static CustomerDto.CustomerDtoBuilder defaultCustomerDto() {
        return CustomerDto.builder()
                       .withName("John Wick")
                       .withDocumentNumber("00000100000")
                       .withType(CustomerTypeEnumDto.NATURAL)
                       .withGender(GenderEnumDto.MALE)
                       .withDateOfBirth(LocalDate.now().minusYears(30))
                       .withEmail("foo@gmail.com")
                       .withPhoneNumber(1130305050);
    }
}
