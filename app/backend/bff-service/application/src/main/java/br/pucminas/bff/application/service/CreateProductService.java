package br.pucminas.bff.application.service;


import br.pucminas.bff.application.domain.Product;
import br.pucminas.bff.application.ports.in.CreateProductUseCase;
import br.pucminas.bff.application.ports.out.products.CreateProductPort;
import br.pucminas.bff.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class CreateProductService implements CreateProductUseCase {

    private final CreateProductPort port;

    @Override
    public Mono<Product> create(Product product) {
        return this.port.create(product);
    }

}
