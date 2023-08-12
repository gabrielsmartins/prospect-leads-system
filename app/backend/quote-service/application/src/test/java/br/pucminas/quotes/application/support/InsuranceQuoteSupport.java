package br.pucminas.quotes.application.support;


import br.pucminas.quotes.application.domain.InsuranceQuote;
import br.pucminas.quotes.application.domain.enums.InsuranceQuoteTypeEnum;
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
                .withType(InsuranceQuoteTypeEnum.LIFE)
                .withCustomer(CustomerSupport.defaultCustomer().build())
                .withProductId(1)
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
