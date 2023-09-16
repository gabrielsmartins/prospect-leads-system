package br.pucminas.products.application.ports.out;

import br.pucminas.products.application.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SearchProductPort {

    Page<Product> findAll(Pageable pageable);
    Optional<Product> findById(Integer id);

}
