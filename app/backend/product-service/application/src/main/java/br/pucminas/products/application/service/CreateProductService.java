package br.pucminas.products.application.service;


import br.pucminas.products.application.domain.Product;
import br.pucminas.products.application.ports.in.CreateProductUseCase;
import br.pucminas.products.application.ports.out.SaveProductPort;
import br.pucminas.products.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateProductService implements CreateProductUseCase {

    private final SaveProductPort port;

    @Override
    public Product create(Product product) {
        product.setActive(true);
        return this.port.save(product);
    }

}
