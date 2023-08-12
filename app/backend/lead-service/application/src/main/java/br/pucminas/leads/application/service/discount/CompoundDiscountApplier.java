package br.pucminas.leads.application.service.discount;

import br.pucminas.leads.application.domain.InsuranceQuote;
import br.pucminas.leads.application.ports.out.UpdateInsuranceQuotePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CompoundDiscountApplier extends DiscountApplier {

    private final List<DiscountApplier> discounts;
    private final UpdateInsuranceQuotePort updateInsuranceQuotePort;

    @Override
    public void apply(InsuranceQuote insuranceQuote) {
        for (var discount : discounts) {
            if (!(discount instanceof CompoundDiscountApplier)) {
                discount.apply(insuranceQuote);
            }
        }
        var id = insuranceQuote.getId();
        this.updateInsuranceQuotePort.update(id, insuranceQuote);
    }

    @Override
    protected boolean satisfyCondition(InsuranceQuote insuranceQuote) {
        return true;
    }

    @Override
    protected BigDecimal calculateNewAmount(InsuranceQuote insuranceQuote) {
        return BigDecimal.ZERO;
    }

    public Integer addDiscount(DiscountApplier discountApplier) {
        this.discounts.add(discountApplier);
        return this.discounts.size();
    }

}
