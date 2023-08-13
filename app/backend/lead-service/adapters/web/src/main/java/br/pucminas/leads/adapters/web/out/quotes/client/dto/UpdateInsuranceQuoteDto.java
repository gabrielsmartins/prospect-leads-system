package br.pucminas.leads.adapters.web.out.quotes.client.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UpdateInsuranceQuoteDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "customer")
    private CustomerDto customer;

    @JsonProperty(value = "product_id")
    private Integer productId;

    @JsonProperty(value = "total_monthly_premium_amount")
    private BigDecimal totalMonthlyPremiumAmount;

    @JsonProperty(value = "total_coverage_amount")
    private BigDecimal totalCoverageAmount;

    @Builder.Default
    @JsonProperty(value = "coverages")
    private final Map<String, BigDecimal> coverages = new LinkedHashMap<>();

    @Builder.Default
    @JsonProperty(value = "assistances")
    private final List<String> assistances = new LinkedList<>();

    @JsonProperty(value = "finished")
    private boolean finished;

    @JsonProperty(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime createdAt;

    @JsonProperty(value = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime updatedAt;

    @JsonProperty(value = "finished_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime finishedAt;

}
