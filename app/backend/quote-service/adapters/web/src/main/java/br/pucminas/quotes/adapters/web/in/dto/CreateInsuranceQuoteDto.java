package br.pucminas.quotes.adapters.web.in.dto;

import br.pucminas.quotes.adapters.web.in.dto.enums.InsuranceQuoteTypeEnumDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
public class CreateInsuranceQuoteDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @JsonProperty(value = "type")
    @NotNull
    private InsuranceQuoteTypeEnumDto type;

    @JsonProperty(value = "customer")
    @NotNull
    @Valid
    private CustomerDto customer;

    @JsonProperty(value = "product_id")
    @NotNull
    private Integer productId;

    @JsonProperty(value = "total_yearly_premium_amount")
    @NotNull
    private BigDecimal totalYearlyPremiumAmount;

    @JsonProperty(value = "total_monthly_premium_amount")
    @NotNull
    private BigDecimal totalMonthlyPremiumAmount;

    @JsonProperty(value = "total_coverage_amount", access = JsonProperty.Access.READ_ONLY)
    private BigDecimal totalCoverageAmount;

    @Builder.Default
    @JsonProperty(value = "coverages")
    @NotEmpty
    private final Map<String, BigDecimal> coverages = new LinkedHashMap<>();

    @Builder.Default
    @JsonProperty(value = "assistances")
    @NotEmpty
    private final List<String> assistances = new LinkedList<>();

    @JsonProperty(value = "created_at", access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime createdAt;

    public Integer addCoverage(String coverage, BigDecimal amount) {
        this.coverages.put(coverage, amount);
        this.totalCoverageAmount = this.totalCoverageAmount.add(amount);
        return this.coverages.size();
    }

    public Integer removeCoverage(String coverage) {
        if (this.coverages.containsKey(coverage)) {
            var coverageAmount = this.coverages.get(coverage);
            this.coverages.remove(coverage);
            this.totalCoverageAmount = this.totalCoverageAmount.subtract(coverageAmount);
        }
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
