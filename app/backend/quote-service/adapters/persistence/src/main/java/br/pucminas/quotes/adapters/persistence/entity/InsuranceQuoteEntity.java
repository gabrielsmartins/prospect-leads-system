package br.pucminas.quotes.adapters.persistence.entity;

import br.pucminas.quotes.application.domain.enums.InsuranceQuoteTypeEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
@Document
public class InsuranceQuoteEntity {

    @Id
    private UUID id;

    @Field("type")
    private InsuranceQuoteTypeEnum type;

    @Field("customer")
    private CustomerEntity customer;

    @Field("product_id")
    private Integer productId;

    @Field("total_monthly_premium_amount")
    private BigDecimal totalMonthlyPremiumAmount;

    @Field("total_coverage_amount")
    private BigDecimal totalCoverageAmount;

    @Builder.Default
    @Field("coverages")
    private Map<String, BigDecimal> coverages = new LinkedHashMap<>();

    @Builder.Default
    @Field("assistances")
    private List<String> assistances = new LinkedList<>();

    @Field("finished")
    private boolean finished;

    @Field("created_at")
    private LocalDateTime createdAt;

    @Field("updated_at")
    private LocalDateTime updatedAt;

    @Field("finished_at")
    private LocalDateTime finishedAt;

}
