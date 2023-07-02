package br.pucminas.products.application.ports.in;

import br.pucminas.products.application.domain.Product;

public interface UpdateProductUseCase {

    Product update(Integer id, Product product);

}
