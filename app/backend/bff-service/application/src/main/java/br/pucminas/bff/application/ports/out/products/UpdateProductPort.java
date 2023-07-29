package br.pucminas.bff.application.ports.out.products;

import br.pucminas.bff.application.domain.Product;
import reactor.core.publisher.Mono;

public interface UpdateProductPort {

    Mono<Product> update(Integer id, Product product);

}
