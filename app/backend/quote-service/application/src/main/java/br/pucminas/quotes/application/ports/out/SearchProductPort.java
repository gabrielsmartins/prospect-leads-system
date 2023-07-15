package br.pucminas.quotes.application.ports.out;

import br.pucminas.quotes.application.domain.Product;
import reactor.core.publisher.Mono;

public interface SearchProductPort {

    Mono<Product> findById(Integer id);

}
