package br.pucminas.quotes.adapters.persistence.entity;

import br.pucminas.quotes.application.domain.enums.CustomerTypeEnum;
import br.pucminas.quotes.application.domain.enums.GenderEnum;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
public class CustomerEntity {

    @Field("document_number")
    private String documentNumber;

    @Field("name")
    private String name;

    @Field("type")
    private CustomerTypeEnum type;

    @Field("gender")
    private GenderEnum gender;

    @Field("date_of_birth")
    private LocalDate dateOfBirth;

    @Field("email")
    private String email;

    @Field("phone_number")
    private Integer phoneNumber;

}
