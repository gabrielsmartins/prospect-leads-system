package br.pucminas.bff.application.domain;

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
    private String type;
    private String gender;
    private LocalDate dateOfBirth;

}
