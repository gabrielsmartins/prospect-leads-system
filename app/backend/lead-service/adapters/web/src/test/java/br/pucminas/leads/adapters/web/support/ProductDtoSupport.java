package br.pucminas.leads.adapters.web.support;


import br.pucminas.leads.adapters.web.out.products.client.dto.CategoryEnumDto;
import br.pucminas.leads.adapters.web.out.products.client.dto.ProductDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDtoSupport {

    public static ProductDto.ProductDtoBuilder defaultProductDto() {
        return ProductDto.builder()
                               .withId(1)
                               .withName("Seguro Vida Individual")
                               .withActive(true)
                               .withCategory(CategoryEnumDto.LIFE)
                               .withMinTotalMonthlyPremiumAmount(BigDecimal.TEN)
                               .withMaxTotalMonthlyPremiumAmount(BigDecimal.valueOf(120))
                               .withSuggestedTotalMonthlyPremiumAmount(BigDecimal.TEN)
                               .withTotalCoverageAmount(BigDecimal.TEN)
                               .withCoverages(new LinkedHashMap<>(Map.of("Acidente", BigDecimal.TEN)))
                               .withAssistances(new LinkedList<>(List.of("Manutenção")))
                               .withCreatedAt(LocalDateTime.now())
                               .withUpdatedAt(LocalDateTime.now())
                               .withDeletedAt(LocalDateTime.now());
    }

}
