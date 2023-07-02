package br.pucminas.products.application.ports.out;

import br.pucminas.products.application.domain.Product;

public interface SaveProductPort {

    Product save(Product product);

}
