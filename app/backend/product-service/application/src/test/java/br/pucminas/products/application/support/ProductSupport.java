package br.pucminas.products.application.support;


import br.pucminas.products.application.domain.enums.CategoryEnum;
import br.pucminas.products.application.domain.Product;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ProductSupport {

    public static Product.ProductBuilder defaultProduct() {
        return Product.builder()
                .withId(1)
                .withName("Seguro Vida Individual")
                .withActive(true)
                .withCategory(CategoryEnum.LIFE)
                .withCreatedAt(LocalDateTime.now())
                .withUpdatedAt(LocalDateTime.now())
                .withDeletedAt(LocalDateTime.now());
    }
}
