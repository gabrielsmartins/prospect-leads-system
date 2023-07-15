package br.pucminas.quotes.adapters.persistence.repository;

import br.pucminas.quotes.adapters.persistence.entity.InsuranceQuoteEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface InsuranceQuoteRepositoryCustom<T extends InsuranceQuoteEntity> extends MongoRepository<T, UUID> {
}
