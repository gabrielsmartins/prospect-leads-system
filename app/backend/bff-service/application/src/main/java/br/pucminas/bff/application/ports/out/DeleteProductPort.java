package br.pucminas.bff.application.ports.out;

import reactor.core.publisher.Mono;

public interface DeleteProductPort {

    Mono<Void> delete(Integer id);

}
