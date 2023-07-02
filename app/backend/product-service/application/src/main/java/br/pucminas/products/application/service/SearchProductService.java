package br.pucminas.products.application.service;

import br.pucminas.products.application.domain.Product;
import br.pucminas.products.application.domain.exceptions.ProductNotFoundException;
import br.pucminas.products.application.ports.in.SearchProductUseCase;
import br.pucminas.products.application.ports.out.SearchProductPort;
import br.pucminas.products.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SearchProductService implements SearchProductUseCase {

    private final SearchProductPort port;

    @Override
    public Product findById(Integer id) {
        return this.port.findById(id)
                        .orElseThrow(() -> new ProductNotFoundException(String.format("Product %s not found", id)));
    }

}
