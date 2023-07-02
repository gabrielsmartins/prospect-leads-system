package br.pucminas.products.adapters.persistence.service;

import br.pucminas.products.adapters.persistence.entity.ProductEntity;
import br.pucminas.products.adapters.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductPersistenceService implements IProductPersistenceService {

    private final ProductRepository repository;

    @Override
    public ProductEntity save(ProductEntity productEntity) {
        return this.repository.save(productEntity);
    }

    @Override
    public Optional<ProductEntity> findById(Integer id) {
        return this.repository.findById(id);
    }

}
