package br.pucminas.bff.adapters.web.in.support;


import br.pucminas.bff.adapters.web.in.products.dto.CreateProductDto;
import br.pucminas.bff.adapters.web.in.products.dto.UpdateProductDto;
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

    public static CreateProductDto.CreateProductDtoBuilder defaultCreateProductDto() {
        return CreateProductDto.builder()
                               .withId(1)
                               .withName("Seguro Vida Individual")
                               .withActive(true)
                               .withCategory("LIFE")
                               .withMinTotalMonthlyPremiumAmount(BigDecimal.TEN)
                               .withMaxTotalMonthlyPremiumAmount(BigDecimal.valueOf(120))
                               .withSuggestedTotalMonthlyPremiumAmount(BigDecimal.TEN)
                               .withTotalCoverageAmount(BigDecimal.TEN)
                               .withCoverages(new LinkedHashMap<>(Map.of("Acidente", BigDecimal.TEN)))
                               .withAssistances(new LinkedList<>(List.of("Manutenção")))
                               .withCreatedAt(LocalDateTime.now());
    }

    public static UpdateProductDto.UpdateProductDtoBuilder defaultUpdateProductDto() {
        return UpdateProductDto.builder()
                               .withId(1)
                               .withName("Seguro Vida Individual")
                               .withActive(true)
                               .withCategory("LIFE")
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
