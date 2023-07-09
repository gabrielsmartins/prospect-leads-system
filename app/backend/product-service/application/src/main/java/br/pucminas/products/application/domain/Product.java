package br.pucminas.products.application.domain;

import br.pucminas.products.application.domain.enums.CategoryEnum;
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
public class Product {

    private Integer id;
    private String name;
    private CategoryEnum category;
    private boolean active;

    private BigDecimal totalYearlyPremiumAmount;

    private BigDecimal totalMonthlyPremiumAmount;

    @Builder.Default
    @Setter(AccessLevel.NONE)
    private BigDecimal totalCoverageAmount = BigDecimal.ZERO;

    @Builder.Default
    @Getter(AccessLevel.NONE)
    private final Map<String, BigDecimal> coverages = new LinkedHashMap<>();

    @Builder.Default
    @Getter(AccessLevel.NONE)
    private final List<String> assistances = new LinkedList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

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

    public Map<String, BigDecimal> getCoverages() {
        return Collections.unmodifiableMap(this.coverages);
    }

    public List<String> getAssistances() {
        return Collections.unmodifiableList(this.assistances);
    }

}
