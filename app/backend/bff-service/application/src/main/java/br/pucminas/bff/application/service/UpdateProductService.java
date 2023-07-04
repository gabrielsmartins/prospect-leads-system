package br.pucminas.bff.application.service;

import br.pucminas.bff.application.domain.Product;
import br.pucminas.bff.application.ports.in.UpdateProductUseCase;
import br.pucminas.bff.application.ports.out.UpdateProductPort;
import br.pucminas.bff.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class UpdateProductService implements UpdateProductUseCase {

    private final UpdateProductPort port;

    @Override
    public Mono<Product> update(Integer id, Product product) {
        return this.port.update(id, product);
    }

}
