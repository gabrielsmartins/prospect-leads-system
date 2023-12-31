package br.pucminas.products.application.service;

import br.pucminas.products.application.domain.Product;
import br.pucminas.products.application.domain.exceptions.ProductNotFoundException;
import br.pucminas.products.application.ports.in.SearchProductUseCase;
import br.pucminas.products.application.ports.out.SearchProductPort;
import br.pucminas.products.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
@RequiredArgsConstructor
public class SearchProductService implements SearchProductUseCase {

    private final SearchProductPort port;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return this.port.findAll(pageable);
    }

    @Override
    public Product findById(Integer id) {
        return this.port.findById(id)
                        .orElseThrow(() -> new ProductNotFoundException(String.format("Product %s not found", id)));
    }

}
