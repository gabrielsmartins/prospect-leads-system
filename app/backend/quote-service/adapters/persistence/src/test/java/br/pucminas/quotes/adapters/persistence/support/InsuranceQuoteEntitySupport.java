package br.pucminas.quotes.adapters.persistence.support;


import br.pucminas.quotes.adapters.persistence.entity.InsuranceQuoteEntity;
import br.pucminas.quotes.application.domain.enums.InsuranceQuoteTypeEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static br.pucminas.quotes.adapters.persistence.support.CustomerEntitySupport.defaultCustomerEntity;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InsuranceQuoteEntitySupport {

    public static InsuranceQuoteEntity.InsuranceQuoteEntityBuilder defaultInsuranceQuoteEntity() {
        return InsuranceQuoteEntity.builder()
                .withId(UUID.randomUUID())
                .withType(InsuranceQuoteTypeEnum.LIFE)
                .withCustomer(defaultCustomerEntity().build())
                .withProductId(1)
                .withTotalYearlyPremiumAmount(BigDecimal.valueOf(120))
                .withTotalMonthlyPremiumAmount(BigDecimal.TEN)
                .withCoverages(new LinkedHashMap<>(Map.of("Acidente", BigDecimal.TEN)))
                .withAssistances(new LinkedList<>(List.of("Manutenção")))
                .withFinished(true)
                .withCreatedAt(LocalDateTime.now())
                .withUpdatedAt(LocalDateTime.now())
                .withFinishedAt(LocalDateTime.now());
    }

}
