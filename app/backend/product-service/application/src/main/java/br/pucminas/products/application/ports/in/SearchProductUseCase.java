package br.pucminas.products.application.ports.in;

import br.pucminas.products.application.domain.Product;

public interface SearchProductUseCase {

    Product findById(Integer id);

}
