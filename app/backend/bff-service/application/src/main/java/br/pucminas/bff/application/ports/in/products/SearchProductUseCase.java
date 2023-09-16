package br.pucminas.bff.application.ports.in.products;

import br.pucminas.bff.application.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface SearchProductUseCase {

    Mono<Page<Product>> findAll(Pageable pageable);
    Mono<Product> findById(Integer id);

}
