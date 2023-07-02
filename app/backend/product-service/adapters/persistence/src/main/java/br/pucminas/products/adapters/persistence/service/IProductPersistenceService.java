package br.pucminas.products.adapters.persistence.service;

import br.pucminas.products.adapters.persistence.entity.ProductEntity;

import java.util.Optional;

public interface IProductPersistenceService {

    ProductEntity save(ProductEntity productEntity);

    Optional<ProductEntity> findById(Integer id);

}
