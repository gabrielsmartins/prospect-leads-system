package br.pucminas.leads.application.service.discount;

import br.pucminas.leads.application.domain.InsuranceQuote;

import java.math.BigDecimal;

public abstract class DiscountApplier {

    public void apply(InsuranceQuote insuranceQuote) {
        if (this.satisfyCondition(insuranceQuote)) {
            var newTotalMonthlyPremiumAmount = calculateNewAmount(insuranceQuote);
            var product = insuranceQuote.getProduct();
            var minTotalMonthlyPremiumAmount = product.getMinTotalMonthlyPremiumAmount();
            if (!newTotalMonthlyPremiumAmount.equals(BigDecimal.ZERO) && newTotalMonthlyPremiumAmount.compareTo(minTotalMonthlyPremiumAmount) >= 0) {
                insuranceQuote.setTotalMonthlyPremiumAmount(newTotalMonthlyPremiumAmount);
            }
        }
    }

    protected abstract boolean satisfyCondition(InsuranceQuote insuranceQuote);

    protected abstract BigDecimal calculateNewAmount(InsuranceQuote insuranceQuote);

}
