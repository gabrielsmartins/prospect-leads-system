package br.pucminas.products.adapters.persistence.service;

import br.pucminas.products.adapters.persistence.entity.ProductEntity;
import br.pucminas.products.adapters.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<ProductEntity> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Override
    public Optional<ProductEntity> findById(Integer id) {
        return this.repository.findById(id);
    }

}
