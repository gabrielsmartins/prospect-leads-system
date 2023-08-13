package br.pucminas.quotes.application.domain;

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
    private String category;
    private boolean active;

    @Builder.Default
    private BigDecimal maxTotalMonthlyPremiumAmount = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal minTotalMonthlyPremiumAmount = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal suggestedTotalMonthlyPremiumAmount = BigDecimal.ZERO;
    private BigDecimal totalCoverageAmount;

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

    public Map<String, BigDecimal> getCoverages() {
        return Collections.unmodifiableMap(this.coverages);
    }

    public List<String> getAssistances() {
        return Collections.unmodifiableList(this.assistances);
    }

}
