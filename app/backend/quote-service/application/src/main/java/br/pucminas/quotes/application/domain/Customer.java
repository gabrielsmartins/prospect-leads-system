package br.pucminas.quotes.application.domain;

import br.pucminas.quotes.application.domain.enums.CustomerTypeEnum;
import br.pucminas.quotes.application.domain.enums.GenderEnum;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
public class Customer {

    private String documentNumber;
    private String name;
    private CustomerTypeEnum type;
    private GenderEnum gender;
    private LocalDate dateOfBirth;
    private String email;
    private Integer phoneNumber;
}
