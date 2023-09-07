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
public class ProductEntity {

    @DynamoDBAttribute(attributeName="Id")
    private Integer id;

    @DynamoDBAttribute(attributeName="Name")
    private String name;

    @DynamoDBAttribute(attributeName="Category")
    private String category;

    @DynamoDBAttribute(attributeName="Active")
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    private Boolean active;

    @DynamoDBAttribute(attributeName="MaxTotalMonthlyPremiumAmount")
    private BigDecimal maxTotalMonthlyPremiumAmount;

    @DynamoDBAttribute(attributeName="MinTotalMonthlyPremiumAmount")
    private BigDecimal minTotalMonthlyPremiumAmount;

    @DynamoDBAttribute(attributeName="SuggestedTotalMonthlyPremiumAmount")
    private BigDecimal suggestedTotalMonthlyPremiumAmount;

    @DynamoDBAttribute(attributeName="TotalCoverageAmount")
    private BigDecimal totalCoverageAmount;

    @DynamoDBAttribute(attributeName="Coverages")
    @Builder.Default
    private Map<String, BigDecimal> coverages = new LinkedHashMap<>();

    @DynamoDBAttribute(attributeName="Assistances")
    @Builder.Default
    private List<String> assistances = new LinkedList<>();

    @DynamoDBAttribute(attributeName="CreatedAt")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @DynamoDBAttribute(attributeName="UpdatedAt")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime updatedAt;

    @DynamoDBAttribute(attributeName="DeletedAt")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime deletedAt;

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
