package br.pucminas.leads.adapters.persistence.entity;

import br.pucminas.leads.adapters.persistence.entity.converter.LocalDateTimeConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
@DynamoDBTable(tableName="Leads")
public class LeadEntity {

    @DynamoDBHashKey(attributeName = "Id")
    private UUID insuranceQuoteId;

    @DynamoDBAttribute(attributeName="CreatedAt")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @DynamoDBAttribute(attributeName="ProcessedAt")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime processedAt;

    @DynamoDBAttribute(attributeName="Sent")
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    private boolean sent;

}
