package br.pucminas.products.adapters.persistence.support;


import br.pucminas.products.adapters.persistence.entity.ProductEntity;
import br.pucminas.products.application.domain.enums.CategoryEnum;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class ProductEntitySupport {

    public static ProductEntity.ProductEntityBuilder defaultProductEntity() {
        return ProductEntity.builder()
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
