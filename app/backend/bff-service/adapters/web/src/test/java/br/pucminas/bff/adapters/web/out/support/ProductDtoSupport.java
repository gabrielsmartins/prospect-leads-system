package br.pucminas.bff.adapters.web.out.support;


import br.pucminas.bff.adapters.web.out.products.dto.CategoryEnumDto;
import br.pucminas.bff.adapters.web.out.products.dto.CreateProductDto;
import br.pucminas.bff.adapters.web.out.products.dto.SearchProductDto;
import br.pucminas.bff.adapters.web.out.products.dto.UpdateProductDto;
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

    public static SearchProductDto.SearchProductDtoBuilder defaultSearchProductDto() {
        return SearchProductDto.builder()
                .withId(1)
                .withName("Seguro Vida Individual")
                .withActive(true)
                .withCategory(CategoryEnumDto.LIFE)
                .withCreatedAt(LocalDateTime.now())
                .withUpdatedAt(LocalDateTime.now())
                .withDeletedAt(LocalDateTime.now());
    }

}
