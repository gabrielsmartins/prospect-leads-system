package br.pucminas.leads.application.service.discount;

import br.pucminas.leads.application.domain.InsuranceQuote;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class GenderDiscountApplier extends DiscountApplier {

    private static final String GENDER = "F";
    private static final double PERCENTAGE_DISCOUNT = 0.05;

    @Override
    protected boolean satisfyCondition(InsuranceQuote insuranceQuote) {
        var customer = insuranceQuote.getCustomer();
        var gender = customer.getGender();
        return gender.equalsIgnoreCase(GENDER);
    }

    @Override
    protected BigDecimal calculateNewAmount(InsuranceQuote insuranceQuote) {
        var totalMonthlyPremiumAmount = insuranceQuote.getTotalMonthlyPremiumAmount();
        var discount = totalMonthlyPremiumAmount.multiply(BigDecimal.valueOf(PERCENTAGE_DISCOUNT));
        return totalMonthlyPremiumAmount.subtract(discount);
    }

}
