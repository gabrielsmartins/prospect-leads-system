package br.pucminas.quotes.application.support;

import br.pucminas.quotes.application.domain.Customer;
import br.pucminas.quotes.application.domain.enums.CustomerTypeEnum;
import br.pucminas.quotes.application.domain.enums.GenderEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerSupport {

    public static Customer.CustomerBuilder defaultCustomer() {
        return Customer.builder()
                       .withName("John Wick")
                       .withDocumentNumber("00000100000")
                       .withType(CustomerTypeEnum.NATURAL)
                       .withGender(GenderEnum.MALE)
                       .withDateOfBirth(LocalDate.now().minusYears(30));
    }
}
