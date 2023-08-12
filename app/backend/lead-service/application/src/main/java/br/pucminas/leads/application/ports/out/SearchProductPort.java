package br.pucminas.leads.application.ports.out;

import br.pucminas.leads.application.domain.Product;

import java.util.Optional;

public interface SearchProductPort {

    Optional<Product> findById(Integer id);

}
