package br.pucminas.bff.application.service.products;

import br.pucminas.bff.application.domain.Product;
import br.pucminas.bff.application.ports.in.products.SearchProductUseCase;
import br.pucminas.bff.application.ports.out.products.SearchProductPort;
import br.pucminas.bff.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class SearchProductService implements SearchProductUseCase {

    private final SearchProductPort port;

    @Override
    public Mono<Page<Product>> findAll(Pageable pageable) {
        return this.port.findAll(pageable);
    }

    @Override
    public Mono<Product> findById(Integer id) {
        return this.port.findById(id);
    }

}
