package br.pucminas.leads.adapters.persistence.entity;

import br.pucminas.leads.adapters.persistence.entity.converter.LocalDateConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
@DynamoDBDocument
public class CustomerEntity {

    @DynamoDBAttribute(attributeName="DocumentNumber")
    private String documentNumber;

    @DynamoDBAttribute(attributeName="Name")
    private String name;

    @DynamoDBAttribute(attributeName="Type")
    private String type;

    @DynamoDBAttribute(attributeName="Gender")
    private String gender;

    @DynamoDBAttribute(attributeName="DateOfBirth")
    @DynamoDBTypeConverted(converter = LocalDateConverter.class)
    private LocalDate dateOfBirth;

    @DynamoDBAttribute(attributeName="Email")
    private String email;

    @DynamoDBAttribute(attributeName="PhoneNumber")
    private Integer phoneNumber;

}
