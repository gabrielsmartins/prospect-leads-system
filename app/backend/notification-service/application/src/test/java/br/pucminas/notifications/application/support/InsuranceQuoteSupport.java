package br.pucminas.notifications.application.support;


import br.pucminas.notifications.application.domain.InsuranceQuote;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InsuranceQuoteSupport {

    public static InsuranceQuote.InsuranceQuoteBuilder defaultInsuranceQuote() {
        return InsuranceQuote.builder()
                .withId(UUID.randomUUID())
                .withType("L")
                .withCustomer(CustomerSupport.defaultCustomer().build())
                .withProduct(ProductSupport.defaultProduct().build())
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
