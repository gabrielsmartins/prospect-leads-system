package br.pucminas.bff.application.service;

import br.pucminas.bff.application.ports.in.DeleteProductUseCase;
import br.pucminas.bff.application.ports.out.DeleteProductPort;
import br.pucminas.bff.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class DeleteProductService implements DeleteProductUseCase {

    private final DeleteProductPort port;

    @Override
    public Mono<Void> delete(Integer id) {
        return this.port.delete(id);
    }

}
