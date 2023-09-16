package br.pucminas.products.adapters.persistence.service;

import br.pucminas.products.adapters.persistence.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IProductPersistenceService {

    ProductEntity save(ProductEntity productEntity);

    Page<ProductEntity> findAll(Pageable pageable);

    Optional<ProductEntity> findById(Integer id);



}
