package br.pucminas.products.application.service;

import br.pucminas.products.application.domain.Product;
import br.pucminas.products.application.domain.exceptions.ProductNotFoundException;
import br.pucminas.products.application.ports.in.UpdateProductUseCase;
import br.pucminas.products.application.ports.out.SaveProductPort;
import br.pucminas.products.application.ports.out.SearchProductPort;
import br.pucminas.products.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateProductService implements UpdateProductUseCase {

    private final SearchProductPort searchProductPort;
    private final SaveProductPort saveProductPort;

    @Override
    public Product update(Integer id, Product product) {
        var existingProduct = this.searchProductPort.findById(id)
                                                    .orElseThrow(() -> new ProductNotFoundException(String.format("Product %s not found", id)));
        var isNotTheSameId = !existingProduct.getId().equals(product.getId());
        if (isNotTheSameId) {
            throw new ProductNotFoundException("Product id is not the same as the informed");
        }
        return this.saveProductPort.save(product);
    }

}
