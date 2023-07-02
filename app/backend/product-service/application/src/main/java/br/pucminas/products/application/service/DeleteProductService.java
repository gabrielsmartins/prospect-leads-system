package br.pucminas.products.application.service;

import br.pucminas.products.application.domain.exceptions.ProductNotFoundException;
import br.pucminas.products.application.ports.in.DeleteProductUseCase;
import br.pucminas.products.application.ports.out.SaveProductPort;
import br.pucminas.products.application.ports.out.SearchProductPort;
import br.pucminas.products.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@UseCase
@RequiredArgsConstructor
public class DeleteProductService implements DeleteProductUseCase {

    private final SearchProductPort searchProductPort;
    private final SaveProductPort saveProductPort;

    @Override
    public void delete(Integer id) {
        var existingProduct = this.searchProductPort.findById(id)
                                                    .orElseThrow(() -> new ProductNotFoundException(String.format("Product %s not found", id)));
        existingProduct.setActive(false);
        existingProduct.setDeletedAt(LocalDateTime.now());
        this.saveProductPort.save(existingProduct);
    }

}
