package br.pucminas.leads.application.domain;

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
public class InsuranceQuote {

    private UUID id;
    private String type;
    private Customer customer;
    private Product product;
    private BigDecimal totalMonthlyPremiumAmount;
    private BigDecimal totalCoverageAmount;

    @Builder.Default
    @Getter(AccessLevel.NONE)
    private final Map<String, BigDecimal> coverages = new LinkedHashMap<>();

    @Builder.Default
    @Getter(AccessLevel.NONE)
    private final List<String> assistances = new LinkedList<>();

    private boolean finished;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime finishedAt;

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
