package br.pucminas.leads.application.support;


import br.pucminas.leads.application.domain.InsuranceQuote;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static br.pucminas.leads.application.support.CustomerSupport.defaultCustomer;
import static br.pucminas.leads.application.support.ProductSupport.defaultProduct;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InsuranceQuoteSupport {

    public static InsuranceQuote.InsuranceQuoteBuilder defaultInsuranceQuote() {
        return InsuranceQuote.builder()
                .withId(UUID.randomUUID())
                .withType("LIFE")
                .withCustomer(defaultCustomer().build())
                .withProductId(1)
                .withProduct(defaultProduct().build())
                .withTotalMonthlyPremiumAmount(BigDecimal.TEN)
                .withTotalCoverageAmount(BigDecimal.TEN)
                .withCoverages(new LinkedHashMap<>(Map.of("Acidente", BigDecimal.TEN)))
                .withAssistances(new LinkedList<>(List.of("Manutenção")))
                .withFinished(true)
                .withCreatedAt(LocalDateTime.now())
                .withUpdatedAt(LocalDateTime.now())
                .withFinishedAt(LocalDateTime.now());
    }

}
