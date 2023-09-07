package br.pucminas.leads.adapters.persistence.support;


import br.pucminas.leads.adapters.persistence.entity.InsuranceQuoteEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static br.pucminas.leads.adapters.persistence.support.CustomerEntitySupport.defaultCustomerEntity;
import static br.pucminas.leads.adapters.persistence.support.ProductEntitySupport.defaultProductEntity;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InsuranceQuoteEntitySupport {

    public static InsuranceQuoteEntity.InsuranceQuoteEntityBuilder defaultInsuranceQuoteEntity() {
        return InsuranceQuoteEntity.builder()
                .withId(UUID.randomUUID())
                .withType("L")
                .withCustomer(defaultCustomerEntity().build())
                .withProductId(1)
                .withProduct(defaultProductEntity().build())
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
