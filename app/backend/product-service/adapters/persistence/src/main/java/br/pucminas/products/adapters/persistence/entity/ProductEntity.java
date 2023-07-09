package br.pucminas.products.adapters.persistence.entity;


import br.pucminas.products.adapters.persistence.entity.converter.CategoryEnumConverter;
import br.pucminas.products.application.domain.enums.CategoryEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder(setterPrefix = "with")
@Table(name = "tbl_product")
@Entity
@SelectBeforeUpdate(value = false)
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", columnDefinition = "serial", nullable = false)
    private Integer id;

    @Column(name = "product_name", columnDefinition = "varchar(256)", nullable = false)
    private String name;

    @Column(name = "product_category", columnDefinition = "char(1)", nullable = false)
    @Convert(converter = CategoryEnumConverter.class)
    private CategoryEnum category;

    @Column(name = "total_yearly_premium_amount", columnDefinition = "decimal(17,2)", nullable = false)
    private BigDecimal totalYearlyPremiumAmount;

    @Column(name = "total_monthly_premium_amount", columnDefinition = "decimal(17,2)", nullable = false)
    private BigDecimal totalMonthlyPremiumAmount;

    @Column(name = "total_coverage_amount", columnDefinition = "decimal(17,2)", nullable = false)
    private BigDecimal totalCoverageAmount;

    @Builder.Default
    @Getter(AccessLevel.NONE)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "tbl_coverage",
                     joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"))
    @MapKeyColumn(name = "coverage_name", columnDefinition = "varchar(256)", nullable = false)
    @Column(name = "coverage_amount", columnDefinition = "decimal(17,2)", nullable = false)
    private final Map<String, BigDecimal> coverages = new LinkedHashMap<>();

    @Builder.Default
    @Getter(AccessLevel.NONE)
    @ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "tbl_assistance",
                     joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"))
    @Column(name = "assistances", columnDefinition = "varchar(1024)", nullable = false)
    private final List<String> assistances = new LinkedList<>();

    @Column(name = "product_active", columnDefinition = "boolean", nullable = false)
    private Boolean active;

    @Column(name = "created_at", columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "timestamp")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "timestamp", nullable = true)
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
