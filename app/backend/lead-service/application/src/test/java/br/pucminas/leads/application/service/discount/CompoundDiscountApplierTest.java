package br.pucminas.leads.application.service.discount;

import br.pucminas.leads.application.ports.out.UpdateInsuranceQuotePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static br.pucminas.leads.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static org.mockito.Mockito.*;

class CompoundDiscountApplierTest {

    private CompoundDiscountApplier discountApplier;
    private UpdateInsuranceQuotePort updateInsuranceQuotePort;
    private AgeDiscountApplier ageDiscountApplier;
    private WeekendDiscountApplier weekendDiscountApplier;
    private GenderDiscountApplier genderDiscountApplier;

    @BeforeEach
    public void setup() {
        this.ageDiscountApplier = mock(AgeDiscountApplier.class);
        this.weekendDiscountApplier = mock(WeekendDiscountApplier.class);
        this.genderDiscountApplier = mock(GenderDiscountApplier.class);
        var discountAppliers = new ArrayList<>(List.of(ageDiscountApplier, weekendDiscountApplier, genderDiscountApplier));
        this.updateInsuranceQuotePort = mock(UpdateInsuranceQuotePort.class);
        this.discountApplier = new CompoundDiscountApplier(discountAppliers, updateInsuranceQuotePort);
    }

    @Test
    @DisplayName("Given Discounts When Apply Then Update Insurance Quote")
    public void givenDiscountsWhenApplyThenUpdateInsuranceQuote() {
        var insuranceQuote = defaultInsuranceQuote().build();
        this.discountApplier.apply(insuranceQuote);
        verify(this.ageDiscountApplier, times(1)).apply(insuranceQuote);
        verify(this.weekendDiscountApplier, times(1)).apply(insuranceQuote);
        verify(this.genderDiscountApplier, times(1)).apply(insuranceQuote);
        verify(this.updateInsuranceQuotePort, times(1)).update(any(UUID.class), eq(insuranceQuote));
    }

    @Test
    @DisplayName("Given Discounts When Exists Same Instance Then Update Insurance Quote And Skip")
    public void givenDiscountsWhenExistsSameInstanceThenUpdateInsuranceQuoteAndSkip() {
        var insuranceQuote = defaultInsuranceQuote().build();
        var compoundDiscountApplier = mock(CompoundDiscountApplier.class);
        this.discountApplier.addDiscount(compoundDiscountApplier);
        this.discountApplier.apply(insuranceQuote);
        verify(this.ageDiscountApplier, times(1)).apply(insuranceQuote);
        verify(this.weekendDiscountApplier, times(1)).apply(insuranceQuote);
        verify(this.genderDiscountApplier, times(1)).apply(insuranceQuote);
        verify(compoundDiscountApplier, never()).apply(insuranceQuote);
        verify(this.updateInsuranceQuotePort, times(1)).update(any(UUID.class), eq(insuranceQuote));
    }

}