package br.pucminas.leads.adapters.persistence.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
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
    private LocalDate dateOfBirth;

    @DynamoDBAttribute(attributeName="Email")
    private String email;

    @DynamoDBAttribute(attributeName="PhoneNumber")
    private Integer phoneNumber;

}
