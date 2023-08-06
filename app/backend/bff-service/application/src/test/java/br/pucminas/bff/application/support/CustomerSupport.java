package br.pucminas.bff.application.support;


import br.pucminas.bff.application.domain.Customer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerSupport {

    public static Customer.CustomerBuilder defaultCustomer() {
        return Customer.builder()
                       .withName("John Wick")
                       .withDocumentNumber("00000100000")
                       .withType("F")
                       .withGender("M")
                       .withDateOfBirth(LocalDate.now().minusYears(30));
    }

}
