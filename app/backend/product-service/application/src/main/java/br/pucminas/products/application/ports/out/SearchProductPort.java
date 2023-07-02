package br.pucminas.products.application.ports.out;

import br.pucminas.products.application.domain.Product;

import java.util.Optional;

public interface SearchProductPort {

    Optional<Product> findById(Integer id);

}
