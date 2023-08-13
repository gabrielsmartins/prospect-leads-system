package br.pucminas.leads.application.service.discount;

import br.pucminas.leads.application.domain.InsuranceQuote;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.DayOfWeek;

@Component
public class WeekendDiscountApplier extends DiscountApplier {

    private static final double PERCENTAGE_DISCOUNT = 0.10;

    @Override
    protected boolean satisfyCondition(InsuranceQuote insuranceQuote) {
        var createdAt = insuranceQuote.getCreatedAt();
        var dayOfWeek = createdAt.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    @Override
    protected BigDecimal calculateNewAmount(InsuranceQuote insuranceQuote) {
        var totalMonthlyPremiumAmount = insuranceQuote.getTotalMonthlyPremiumAmount();
        var discount = totalMonthlyPremiumAmount.multiply(BigDecimal.valueOf(PERCENTAGE_DISCOUNT));
        return totalMonthlyPremiumAmount.subtract(discount);
    }


}
