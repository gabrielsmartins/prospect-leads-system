package br.pucminas.bff.adapters.web.out.products.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchProductDto {

    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "active")
    private Boolean active;

    @JsonProperty(value = "category")
    private CategoryEnumDto category;

    @JsonProperty(value = "min_total_monthly_premium_amount")
    private BigDecimal minTotalMonthlyPremiumAmount;

    @JsonProperty(value = "max_total_monthly_premium_amount")
    private BigDecimal maxTotalMonthlyPremiumAmount;

    @JsonProperty(value = "suggested_total_monthly_premium_amount")
    private BigDecimal suggestedTotalMonthlyPremiumAmount;

    @JsonProperty(value = "total_coverage_amount")
    private BigDecimal totalCoverageAmount;

    @JsonProperty(value = "coverages")
    @Builder.Default
    private final Map<String, BigDecimal> coverages = new LinkedHashMap<>();

    @JsonProperty(value = "assistances")
    @Builder.Default
    private final List<String> assistances = new LinkedList<>();

    @JsonProperty(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime createdAt;

    @JsonProperty(value = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime updatedAt;

    @JsonProperty(value = "deleted_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime deletedAt;

}
