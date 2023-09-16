package br.pucminas.bff.application.ports.out.products;

import br.pucminas.bff.application.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface SearchProductPort {

    Mono<Page<Product>> findAll(Pageable pageable);
    Mono<Product> findById(Integer id);

}
