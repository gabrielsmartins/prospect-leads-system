package br.pucminas.products.adapters.web.support;


import br.pucminas.products.adapters.web.in.products.dto.CategoryEnumDto;
import br.pucminas.products.adapters.web.in.products.dto.CreateProductDto;
import br.pucminas.products.adapters.web.in.products.dto.UpdateProductDto;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ProductDtoSupport {

    public static CreateProductDto.CreateProductDtoBuilder defaultCreateProductDto() {
        return CreateProductDto.builder()
                .withId(1)
                .withName("Seguro Vida Individual")
                .withActive(true)
                .withCategory(CategoryEnumDto.LIFE)
                .withCreatedAt(LocalDateTime.now());
    }

    public static UpdateProductDto.UpdateProductDtoBuilder defaultUpdateProductDto() {
        return UpdateProductDto.builder()
                .withId(1)
                .withName("Seguro Vida Individual")
                .withActive(true)
                .withCategory(CategoryEnumDto.LIFE)
                .withCreatedAt(LocalDateTime.now())
                .withUpdatedAt(LocalDateTime.now())
                .withDeletedAt(LocalDateTime.now());
    }

}
