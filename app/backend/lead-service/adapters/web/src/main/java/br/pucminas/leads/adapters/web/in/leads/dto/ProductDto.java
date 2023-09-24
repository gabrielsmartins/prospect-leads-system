package br.pucminas.leads.adapters.web.in.leads.dto;

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
public class ProductDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("category")
    private String category;

    @JsonProperty("active")
    private boolean active;

    @JsonProperty("max_total_monthly_premium_amount")
    private BigDecimal maxTotalMonthlyPremiumAmount;

    @JsonProperty("min_total_monthly_premium_amount")
    private BigDecimal minTotalMonthlyPremiumAmount;

    @JsonProperty("suggested_total_monthly_premium_amount")
    private BigDecimal suggestedTotalMonthlyPremiumAmount;

    @JsonProperty("total_coverage_amount")
    private BigDecimal totalCoverageAmount;

    @Builder.Default
    @JsonProperty("coverages")
    private final Map<String, BigDecimal> coverages = new LinkedHashMap<>();

    @Builder.Default
    @JsonProperty("assistances")
    private final List<String> assistances = new LinkedList<>();

    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime updatedAt;

    @JsonProperty("deleted_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
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
