package br.pucminas.bff.adapters.web.out.products.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
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
public class CreateProductDto {

    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "name")
    @NotNull
    private String name;

    @JsonProperty(value = "active")
    private Boolean active;

    @JsonProperty(value = "category")
    @NotNull
    private CategoryEnumDto category;

    @JsonProperty(value = "total_yearly_premium_amount")
    @NotNull
    private BigDecimal totalYearlyPremiumAmount;

    @JsonProperty(value = "total_monthly_premium_amount")
    @NotNull
    private BigDecimal totalMonthlyPremiumAmount;

    @JsonProperty(value = "total_coverage_amount")
    private BigDecimal totalCoverageAmount;

    @JsonProperty(value = "coverages")
    @Builder.Default
    @NotEmpty
    private final Map<String, BigDecimal> coverages = new LinkedHashMap<>();

    @JsonProperty(value = "assistances")
    @Builder.Default
    @NotEmpty
    private final List<String> assistances = new LinkedList<>();

    @JsonProperty(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime createdAt;

}
