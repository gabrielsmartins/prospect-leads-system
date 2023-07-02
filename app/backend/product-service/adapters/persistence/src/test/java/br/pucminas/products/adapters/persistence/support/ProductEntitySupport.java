package br.pucminas.products.adapters.persistence.support;


import br.pucminas.products.adapters.persistence.entity.ProductEntity;
import br.pucminas.products.application.domain.enums.CategoryEnum;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ProductEntitySupport {

    public static ProductEntity.ProductEntityBuilder defaultProductEntity() {
        return ProductEntity.builder()
                .withId(1)
                .withName("Seguro Vida Individual")
                .withActive(true)
                .withCategory(CategoryEnum.LIFE)
                .withCreatedAt(LocalDateTime.now())
                .withUpdatedAt(LocalDateTime.now())
                .withDeletedAt(LocalDateTime.now());
    }
}
