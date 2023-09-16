package br.pucminas.products.adapters.persistence.adapter;

import br.pucminas.products.adapters.persistence.adapter.mapper.ProductPersistenceMapper;
import br.pucminas.products.adapters.persistence.service.IProductPersistenceService;
import br.pucminas.products.application.domain.Product;
import br.pucminas.products.application.ports.out.SaveProductPort;
import br.pucminas.products.application.ports.out.SearchProductPort;
import br.pucminas.products.common.stereotype.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static net.logstash.logback.argument.StructuredArguments.kv;
import static net.logstash.logback.marker.Markers.append;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class ProductPersistenceAdapter implements SaveProductPort, SearchProductPort {

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

    @Override
    public Page<Product> findAll(Pageable pageable) {
        log.info("Searching all products");
        var pageEntity = this.service.findAll(pageable);
        log.info(append("products", pageEntity), "Products were found successfully");

        log.info(append("products", pageEntity), "Mapping product entities");
        var page = ProductPersistenceMapper.mapToDomain(pageEntity);
        log.info(append("products", page), "Products were mapped successfully");
        return page;
    }

    @Override
    public Optional<Product> findById(Integer id) {
        log.info("Searching product by id :{}", kv("id", id));
        return this.service.findById(id)
                           .map(ProductPersistenceMapper::mapToDomain);
    }

}
