package br.pucminas.leads.adapters.email.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class EmailDto {

    @JsonProperty("from_email")
    private String fromEmail;

    @JsonProperty("to_email")
    private String toEmail;

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("total_monthly_premium_amount")
    private BigDecimal totalMonthlyPremiumAmount;

    @JsonProperty("total_coverage_amount")
    private BigDecimal totalCoverageAmount;

    @Builder.Default
    @JsonProperty("coverages")
    private Map<String, BigDecimal> coverages = new LinkedHashMap<>();

    @Builder.Default
    @JsonProperty("assistances")
    private List<String> assistances = new LinkedList<>();

}
