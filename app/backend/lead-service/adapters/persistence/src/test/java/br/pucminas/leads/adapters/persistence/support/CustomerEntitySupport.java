package br.pucminas.leads.adapters.persistence.support;


import br.pucminas.leads.adapters.persistence.entity.CustomerEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerEntitySupport {

    public static CustomerEntity.CustomerEntityBuilder defaultCustomerEntity() {
        return CustomerEntity.builder()
                       .withName("John Wick")
                       .withDocumentNumber("00000100000")
                       .withType("F")
                       .withGender("M")
                       .withDateOfBirth(LocalDate.now().minusYears(30))
                       .withEmail("foo@gmail.com")
                       .withPhoneNumber(1160604040);
    }

}
