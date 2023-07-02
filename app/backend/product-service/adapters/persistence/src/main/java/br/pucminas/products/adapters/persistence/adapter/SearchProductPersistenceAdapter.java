package br.pucminas.products.adapters.persistence.adapter;

import br.pucminas.products.adapters.persistence.adapter.mapper.ProductPersistenceMapper;
import br.pucminas.products.adapters.persistence.service.IProductPersistenceService;
import br.pucminas.products.application.domain.Product;
import br.pucminas.products.application.ports.out.SearchProductPort;
import br.pucminas.products.common.stereotype.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class SearchProductPersistenceAdapter implements SearchProductPort {

    private final IProductPersistenceService service;

    @Override
    public Optional<Product> findById(Integer id) {
        return this.service.findById(id)
                           .map(ProductPersistenceMapper::mapToDomain);
    }

}
