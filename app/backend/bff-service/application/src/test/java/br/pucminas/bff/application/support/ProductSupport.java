package br.pucminas.bff.application.support;


import br.pucminas.bff.application.domain.Product;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ProductSupport {

    public static Product.ProductBuilder defaultProduct() {
        return Product.builder()
                .withId(1)
                .withName("Seguro Vida Individual")
                .withActive(true)
                .withCategory("LIFE")
                .withCreatedAt(LocalDateTime.now())
                .withUpdatedAt(LocalDateTime.now())
                .withDeletedAt(LocalDateTime.now());
    }
}
