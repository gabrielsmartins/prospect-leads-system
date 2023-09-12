package br.pucminas.leads.application.support;


import br.pucminas.leads.application.domain.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductSupport {

    public static Product.ProductBuilder defaultProduct() {
        return Product.builder()
                      .withId(1)
                      .withName("Seguro Vida Individual")
                      .withActive(true)
                      .withMinTotalMonthlyPremiumAmount(BigDecimal.TEN)
                      .withMaxTotalMonthlyPremiumAmount(BigDecimal.valueOf(100))
                      .withSuggestedTotalMonthlyPremiumAmount(BigDecimal.TEN)
                      .withTotalCoverageAmount(BigDecimal.TEN)
                      .withCategory("LIFE")
                      .withCreatedAt(LocalDateTime.now())
                      .withUpdatedAt(LocalDateTime.now())
                      .withCoverages(new LinkedHashMap<>(Map.of("Acidente", BigDecimal.TEN)))
                      .withAssistances(new LinkedList<>(List.of("Manutenção")));
    }

}
