package br.pucminas.leads.application.service.discount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static br.pucminas.leads.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static org.assertj.core.api.Assertions.assertThat;

class WeekendDiscountApplierTest {

    private WeekendDiscountApplier discountApplier;
    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @BeforeEach
    public void setup() {
        this.discountApplier = new WeekendDiscountApplier();
    }

    @Test
    @DisplayName("Given Insurance Quote When Is Valid Then Apply Discount")
    public void givenInsuranceQuoteWhenIsValidThenApplyDiscount() {
        var insuranceQuote = defaultInsuranceQuote()
                .withTotalMonthlyPremiumAmount(BigDecimal.valueOf(100))
                .build();

        insuranceQuote.setCreatedAt(LocalDateTime.parse("2023-08-05 14:00:39", FORMATTER));
        this.discountApplier.apply(insuranceQuote);
        assertThat(insuranceQuote.getTotalMonthlyPremiumAmount()).isEqualByComparingTo(BigDecimal.valueOf(90));
    }

    @Test
    @DisplayName("Given Insurance Quote When Is Not Valid Then Not Apply Discount")
    public void givenInsuranceQuoteWhenIsValidThenNotApplyDiscount() {
        var insuranceQuote = defaultInsuranceQuote()
                .withTotalMonthlyPremiumAmount(BigDecimal.valueOf(100))
                .build();
        insuranceQuote.setCreatedAt(LocalDateTime.parse("2023-08-07 15:00:39", FORMATTER));
        this.discountApplier.apply(insuranceQuote);
        assertThat(insuranceQuote.getTotalMonthlyPremiumAmount()).isEqualByComparingTo(BigDecimal.valueOf(100));
    }

}