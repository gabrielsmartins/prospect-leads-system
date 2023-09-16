package br.pucminas.products.application.ports.in;

import br.pucminas.products.application.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchProductUseCase {

    Page<Product> findAll(Pageable pageable);

    Product findById(Integer id);

}
