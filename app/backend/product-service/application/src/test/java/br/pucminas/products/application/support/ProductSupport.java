package br.pucminas.products.application.support;


import br.pucminas.products.application.domain.Product;
import br.pucminas.products.application.domain.enums.CategoryEnum;
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
                      .withTotalMonthlyPremiumAmount(BigDecimal.TEN)
                      .withTotalYearlyPremiumAmount(BigDecimal.valueOf(120))
                      .withCategory(CategoryEnum.LIFE)
                      .withCreatedAt(LocalDateTime.now())
                      .withUpdatedAt(LocalDateTime.now())
                      .withDeletedAt(LocalDateTime.now())
                      .withCoverages(new LinkedHashMap<>(Map.of("Acidente", BigDecimal.TEN)))
                      .withAssistances(new LinkedList<>(List.of("Manutenção")));
    }

}
