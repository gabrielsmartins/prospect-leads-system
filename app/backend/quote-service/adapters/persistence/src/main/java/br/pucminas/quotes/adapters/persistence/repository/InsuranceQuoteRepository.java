package br.pucminas.quotes.adapters.persistence.repository;

import br.pucminas.quotes.adapters.persistence.entity.InsuranceQuoteEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InsuranceQuoteRepository extends ReactiveCrudRepository<InsuranceQuoteEntity, UUID> {

}
