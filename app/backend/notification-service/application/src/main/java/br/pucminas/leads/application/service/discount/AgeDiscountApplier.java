package br.pucminas.leads.application.service.discount;

import br.pucminas.leads.application.domain.InsuranceQuote;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.YEARS;

@Component
public class AgeDiscountApplier extends DiscountApplier {

    private static final int MAX_AGE = 45;
    private static final double PERCENTAGE_DISCOUNT = 0.15;

    @Override
    protected boolean satisfyCondition(InsuranceQuote insuranceQuote) {
        var customer = insuranceQuote.getCustomer();
        var age = YEARS.between(customer.getDateOfBirth(), LocalDate.now());
        return age <= MAX_AGE;
    }

    @Override
    protected BigDecimal calculateNewAmount(InsuranceQuote insuranceQuote) {
        var totalMonthlyPremiumAmount = insuranceQuote.getTotalMonthlyPremiumAmount();
        var discount = totalMonthlyPremiumAmount.multiply(BigDecimal.valueOf(PERCENTAGE_DISCOUNT));
        return totalMonthlyPremiumAmount.subtract(discount);
    }

}
