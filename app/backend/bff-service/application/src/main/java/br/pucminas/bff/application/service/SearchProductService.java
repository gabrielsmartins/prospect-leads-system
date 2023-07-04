package br.pucminas.bff.application.service;

import br.pucminas.bff.application.domain.Product;
import br.pucminas.bff.application.ports.in.SearchProductUseCase;
import br.pucminas.bff.application.ports.out.SearchProductPort;
import br.pucminas.bff.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class SearchProductService implements SearchProductUseCase {

    private final SearchProductPort port;

    @Override
    public Mono<Product> findById(Integer id) {
        return this.port.findById(id);
    }

}
