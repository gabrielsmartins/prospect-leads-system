package br.pucminas.bff.adapters.web.in.products.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
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
public class UpdateProductDto {

    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "name")
    @NotNull
    private String name;

    @JsonProperty(value = "active")
    @NotNull
    private Boolean active;

    @JsonProperty(value = "category")
    @NotNull
    private String category;

    @JsonProperty(value = "min_total_monthly_premium_amount")
    private BigDecimal minTotalMonthlyPremiumAmount;

    @JsonProperty(value = "max_total_monthly_premium_amount")
    private BigDecimal maxTotalMonthlyPremiumAmount;

    @JsonProperty(value = "suggested_total_monthly_premium_amount")
    private BigDecimal suggestedTotalMonthlyPremiumAmount;

    @JsonProperty(value = "total_coverage_amount", access = JsonProperty.Access.READ_ONLY)
    private BigDecimal totalCoverageAmount;

    @JsonProperty(value = "coverages")
    @Builder.Default
    private final Map<String, BigDecimal> coverages = new LinkedHashMap<>();

    @JsonProperty(value = "assistances")
    @Builder.Default
    private final List<String> assistances = new LinkedList<>();

    @JsonProperty(value = "created_at", access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime createdAt;

    @JsonProperty(value = "updated_at", access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime updatedAt;

    @JsonProperty(value = "deleted_at", access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime deletedAt;

}
