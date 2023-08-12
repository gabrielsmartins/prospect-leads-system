package br.pucminas.quotes.adapters.persistence.support;

import br.pucminas.quotes.adapters.persistence.entity.CustomerEntity;
import br.pucminas.quotes.application.domain.enums.CustomerTypeEnum;
import br.pucminas.quotes.application.domain.enums.GenderEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerEntitySupport {

    public static CustomerEntity.CustomerEntityBuilder defaultCustomerEntity() {
        return CustomerEntity.builder()
                       .withName("John Wick")
                       .withDocumentNumber("00000100000")
                       .withType(CustomerTypeEnum.NATURAL)
                       .withGender(GenderEnum.MALE)
                       .withDateOfBirth(LocalDate.now().minusYears(30))
                       .withEmail("foo@gmail.com")
                       .withPhoneNumber(1130304040);
    }
}
