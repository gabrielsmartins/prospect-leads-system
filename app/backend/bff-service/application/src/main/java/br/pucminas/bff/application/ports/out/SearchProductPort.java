package br.pucminas.bff.application.ports.out;

import br.pucminas.bff.application.domain.Product;
import reactor.core.publisher.Mono;

public interface SearchProductPort {

    Mono<Product> findById(Integer id);

}
