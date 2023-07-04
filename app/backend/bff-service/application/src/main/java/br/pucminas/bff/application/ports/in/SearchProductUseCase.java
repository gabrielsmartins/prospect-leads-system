package br.pucminas.bff.application.ports.in;

import br.pucminas.bff.application.domain.Product;
import reactor.core.publisher.Mono;

public interface SearchProductUseCase {

    Mono<Product> findById(Integer id);

}
