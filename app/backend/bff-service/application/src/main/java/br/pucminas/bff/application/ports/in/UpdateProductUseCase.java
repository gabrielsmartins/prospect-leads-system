package br.pucminas.bff.application.ports.in;

import br.pucminas.bff.application.domain.Product;
import reactor.core.publisher.Mono;

public interface UpdateProductUseCase {

    Mono<Product> update(Integer id, Product product);

}
