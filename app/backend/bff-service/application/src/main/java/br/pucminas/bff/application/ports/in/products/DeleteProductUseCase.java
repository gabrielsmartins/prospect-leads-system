package br.pucminas.bff.application.ports.in.products;

import reactor.core.publisher.Mono;

public interface DeleteProductUseCase {

    Mono<Void> delete(Integer id);

}
