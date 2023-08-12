package br.pucminas.leads.application.service.discount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static br.pucminas.leads.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static org.assertj.core.api.Assertions.assertThat;

class GenderDiscountApplierTest {

    private GenderDiscountApplier discountApplier;

    @BeforeEach
    public void setup() {
        this.discountApplier = new GenderDiscountApplier();
    }

    @Test
    @DisplayName("Given Insurance Quote When Is Valid Then Apply Discount")
    public void givenInsuranceQuoteWhenIsValidThenApplyDiscount() {
        var insuranceQuote = defaultInsuranceQuote()
                .withTotalMonthlyPremiumAmount(BigDecimal.valueOf(100))
                .build();
        insuranceQuote.getCustomer().setGender("F");
        this.discountApplier.apply(insuranceQuote);
        assertThat(insuranceQuote.getTotalMonthlyPremiumAmount()).isEqualByComparingTo(BigDecimal.valueOf(95));
    }

    @Test
    @DisplayName("Given Insurance Quote When Is Not Valid Then Not Apply Discount")
    public void givenInsuranceQuoteWhenIsValidThenNotApplyDiscount() {
        var insuranceQuote = defaultInsuranceQuote()
                .withTotalMonthlyPremiumAmount(BigDecimal.valueOf(100))
                .build();
        insuranceQuote.getCustomer().setGender("M");
        this.discountApplier.apply(insuranceQuote);
        assertThat(insuranceQuote.getTotalMonthlyPremiumAmount()).isEqualByComparingTo(BigDecimal.valueOf(100));
    }

}