package br.pucminas.products.adapters.persistence.adapter.mapper;


import br.pucminas.products.adapters.persistence.entity.ProductEntity;
import br.pucminas.products.application.domain.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductPersistenceMapper {

    public static ProductEntity mapToEntity(Product product){
        if (product == null) {
            return null;
        }
        return ProductEntity.builder()
                .withId(product.getId())
                .withName(product.getName())
                .withActive(product.isActive())
                .withCategory(product.getCategory())
                .withCreatedAt(product.getCreatedAt())
                .withUpdatedAt(product.getUpdatedAt())
                .withDeletedAt(product.getDeletedAt())
                .build();
    }

    public static Product mapToDomain(ProductEntity productEntity){
        if (productEntity == null) {
            return null;
        }
        return Product.builder()
                .withId(productEntity.getId())
                .withName(productEntity.getName())
                .withActive(productEntity.getActive())
                .withCategory(productEntity.getCategory())
                .withCreatedAt(productEntity.getCreatedAt())
                .withUpdatedAt(productEntity.getUpdatedAt())
                .withDeletedAt(productEntity.getDeletedAt())
                .build();
    }

}
