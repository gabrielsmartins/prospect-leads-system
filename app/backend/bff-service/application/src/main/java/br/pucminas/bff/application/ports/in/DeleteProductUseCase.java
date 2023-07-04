package br.pucminas.bff.application.ports.in;

import reactor.core.publisher.Mono;

public interface DeleteProductUseCase {

    Mono<Void> delete(Integer id);

}
