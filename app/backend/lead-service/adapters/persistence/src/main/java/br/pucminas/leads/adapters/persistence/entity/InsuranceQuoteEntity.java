package br.pucminas.leads.adapters.persistence.entity;

import br.pucminas.leads.adapters.persistence.entity.converter.LocalDateTimeConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
@DynamoDBDocument
public class InsuranceQuoteEntity {

    @DynamoDBAttribute(attributeName="Id")
    private UUID id;

    @DynamoDBAttribute(attributeName="Type")
    private String type;

    @DynamoDBAttribute(attributeName="Customer")
    private CustomerEntity customer;

    @DynamoDBAttribute(attributeName="ProductId")
    private Integer productId;

    @DynamoDBAttribute(attributeName="Product")
    private ProductEntity product;

    @DynamoDBAttribute(attributeName="TotalMonthlyPremiumAmount")
    private BigDecimal totalMonthlyPremiumAmount;

    @DynamoDBAttribute(attributeName="TotalCoverageAmount")
    private BigDecimal totalCoverageAmount;

    @DynamoDBAttribute(attributeName="Coverages")
    @Builder.Default
    private Map<String, BigDecimal> coverages = new LinkedHashMap<>();

    @DynamoDBAttribute(attributeName="Assistances")
    @Builder.Default
    private List<String> assistances = new LinkedList<>();

    @DynamoDBAttribute(attributeName="Finished")
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    private boolean finished;

    @DynamoDBAttribute(attributeName="CreatedAt")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @DynamoDBAttribute(attributeName="UpdatedAt")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime updatedAt;

    @DynamoDBAttribute(attributeName="FinishedAt")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime finishedAt;

    public Integer addCoverage(String coverage, BigDecimal amount) {
        this.coverages.put(coverage, amount);
        return this.coverages.size();
    }

    public Integer removeCoverage(String coverage) {
        this.coverages.remove(coverage);
        return this.coverages.size();
    }

    public Integer addAssistance(String assistance) {
        this.assistances.add(assistance);
        return this.assistances.size();
    }

    public Integer removeAssistance(String assistance) {
        this.assistances.remove(assistance);
        return this.assistances.size();
    }

}
