package br.pucminas.products.application.domain;

import br.pucminas.products.application.domain.exceptions.UnsupportedSuggestedPremiumAmountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProductTest {

    @Test
    @DisplayName("Given Suggested Premium Amount When Is Between Min And Max Then Update Amount")
    public void givenSuggestedPremiumAmountWhenIsBetweenMinAndMaxThenUpdateAmount() {
        var product = new Product();
        product.setMinTotalMonthlyPremiumAmount(BigDecimal.TEN);
        product.setMaxTotalMonthlyPremiumAmount(BigDecimal.valueOf(100));
        product.setSuggestedTotalMonthlyPremiumAmount(BigDecimal.TEN);

        assertThat(product.getSuggestedTotalMonthlyPremiumAmount()).isEqualByComparingTo(BigDecimal.TEN);
    }

    @Test
    @DisplayName("Given Suggested Premium Amount When Min Or Max Is Zero Then Throw Exception")
    public void givenSuggestedPremiumAmountWhenMinOrMaxIsZeroThenThrowException() {
        var product = new Product();

        assertThatThrownBy(() -> product.setSuggestedTotalMonthlyPremiumAmount(BigDecimal.TEN))
                .isInstanceOf(UnsupportedSuggestedPremiumAmountException.class);
    }

    @Test
    @DisplayName("Given Suggested Premium Amount When Is Lower Than Min Then Throw Exception")
    public void givenSuggestedPremiumAmountWhenIsLowerThanMinThenThrowException() {
        var product = new Product();
        product.setMinTotalMonthlyPremiumAmount(BigDecimal.valueOf(11));
        product.setMaxTotalMonthlyPremiumAmount(BigDecimal.TEN);

        assertThatThrownBy(() -> product.setSuggestedTotalMonthlyPremiumAmount(BigDecimal.TEN))
                .isInstanceOf(UnsupportedSuggestedPremiumAmountException.class);
    }

    @Test
    @DisplayName("Given Suggested Premium Amount When Is Greater Than Max Then Throw Exception")
    public void givenSuggestedPremiumAmountWhenIsLowerThanMaxThenThrowException() {
        var product = new Product();
        product.setMinTotalMonthlyPremiumAmount(BigDecimal.TEN);
        product.setMaxTotalMonthlyPremiumAmount(BigDecimal.valueOf(100));

        assertThatThrownBy(() -> product.setSuggestedTotalMonthlyPremiumAmount(BigDecimal.valueOf(150)))
                .isInstanceOf(UnsupportedSuggestedPremiumAmountException.class);
    }

    @Test
    @DisplayName("Given Coverage When Add Then Return Coverages Size")
    public void givenCoverageWhenAddThenReturnCoveragesSize() {
        var product = new Product();

        product.addCoverage("Foo", BigDecimal.TEN);
        var coveragesSize = product.addCoverage("Bar", BigDecimal.TEN);

        assertThat(coveragesSize).isEqualTo(2);
        assertThat(product.getTotalCoverageAmount()).isEqualByComparingTo(BigDecimal.valueOf(20));
    }

    @Test
    @DisplayName("Given Coverage When Remove Then Return Coverages Size")
    public void givenCoverageWhenRemoveThenReturnCoveragesSize() {
        var product = new Product();

        product.addCoverage("Foo", BigDecimal.TEN);
        product.addCoverage("Bar", BigDecimal.TEN);

        var coveragesSize = product.removeCoverage("Foo");

        assertThat(coveragesSize).isEqualTo(1);
        assertThat(product.getTotalCoverageAmount()).isEqualByComparingTo(BigDecimal.TEN);
    }

    @Test
    @DisplayName("Given Assistance When Add Then Return Assistances Size")
    public void givenAssistanceWhenAddThenReturnAssistancesSize() {
        var product = new Product();

        var assistancesSize = product.addAssistance("Bar");

        assertThat(assistancesSize).isEqualTo(1);
    }

    @Test
    @DisplayName("Given Assistance When Remove Then Return Assistances Size")
    public void givenAssistanceWhenRemoveThenReturnAssistancesSize() {
        var product = new Product();

        product.addAssistance("Foo");
        var assistancesSize = product.removeAssistance("Foo");

        assertThat(assistancesSize).isZero();
    }

}