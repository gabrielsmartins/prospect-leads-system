package br.pucminas.quotes.adapters.persistence.service;

import br.pucminas.quotes.adapters.persistence.entity.InsuranceQuoteEntity;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IInsuranceQuoteService {

    Mono<InsuranceQuoteEntity> save(InsuranceQuoteEntity insuranceQuoteEntity);

    Mono<InsuranceQuoteEntity> findById(UUID id);

}
