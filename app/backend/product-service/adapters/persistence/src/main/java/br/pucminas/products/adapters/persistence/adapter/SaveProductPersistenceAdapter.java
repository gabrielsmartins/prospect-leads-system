package br.pucminas.products.adapters.persistence.adapter;

import br.pucminas.products.adapters.persistence.adapter.mapper.ProductPersistenceMapper;
import br.pucminas.products.adapters.persistence.service.IProductPersistenceService;
import br.pucminas.products.application.domain.Product;
import br.pucminas.products.application.ports.out.SaveProductPort;
import br.pucminas.products.common.stereotype.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static net.logstash.logback.marker.Markers.append;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class SaveProductPersistenceAdapter implements SaveProductPort {

    private final IProductPersistenceService service;

    @Override
    public Product save(Product product) {
        log.info(append("product", product), "Mapping product");
        var productEntity = ProductPersistenceMapper.mapToEntity(product);
        log.info(append("product", productEntity), "Product was saved successfully");

        log.info(append("product", productEntity), "Saving product");
        var createdProductEntity = this.service.save(productEntity);
        log.info(append("product", createdProductEntity), "Product was saved successfully");

        log.info(append("product", createdProductEntity), "Mapping saved product");
        var createdProduct = ProductPersistenceMapper.mapToDomain(createdProductEntity);
        log.info(append("product", createdProduct), "Saved product was mapped successfully");

        product.setId(createdProduct.getId());
        product.setCreatedAt(createdProduct.getCreatedAt());
        product.setUpdatedAt(createdProduct.getUpdatedAt());
        product.setDeletedAt(createdProduct.getDeletedAt());
        return createdProduct;
    }

}
