package br.pucminas.bff.adapters.web.in.products.mapper;

import br.pucminas.bff.adapters.web.in.products.dto.CategoryEnumDto;
import br.pucminas.bff.adapters.web.in.products.dto.CreateProductDto;
import br.pucminas.bff.application.domain.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateProductControllerMapper {

    public static CreateProductDto mapToDto(Product product) {
        if (product == null) {
            return null;
        }
        return CreateProductDto.builder()
                        .withId(product.getId())
                        .withName(product.getName())
                        .withActive(product.isActive())
                        .withCategory(CategoryEnumDto.fromDescription(product.getCategory()))
                        .withCreatedAt(product.getCreatedAt())
                        .build();
    }

    public static Product mapToDomain(CreateProductDto productDto) {
        if (productDto == null) {
            return null;
        }
        return Product.builder()
                .withId(productDto.getId())
                .withName(productDto.getName())
                .withCategory(productDto.getCategory().getDescription())
                .build();
    }

}
