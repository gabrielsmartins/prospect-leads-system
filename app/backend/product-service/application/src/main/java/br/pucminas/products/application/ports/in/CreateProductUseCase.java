package br.pucminas.products.application.ports.in;

import br.pucminas.products.application.domain.Product;

public interface CreateProductUseCase {

    Product create(Product product);

}
