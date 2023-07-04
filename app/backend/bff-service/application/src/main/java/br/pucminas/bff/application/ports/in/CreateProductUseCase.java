package br.pucminas.bff.application.ports.in;

import br.pucminas.bff.application.domain.Product;
import reactor.core.publisher.Mono;

public interface CreateProductUseCase {

    Mono<Product> create(Product product);

}
