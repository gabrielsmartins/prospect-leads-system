package br.pucminas.quotes.adapters.persistence.service;

import br.pucminas.quotes.adapters.persistence.entity.InsuranceQuoteEntity;
import br.pucminas.quotes.adapters.persistence.repository.InsuranceQuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InsuranceQuotePersistenceService implements IInsuranceQuotePersistenceService {

    private final InsuranceQuoteRepository repository;

    @Override
    public Mono<InsuranceQuoteEntity> save(InsuranceQuoteEntity insuranceQuoteEntity) {
        return this.repository.save(insuranceQuoteEntity);
    }

    @Override
    public Mono<InsuranceQuoteEntity> findById(UUID id) {
        return this.repository.findById(id);
    }
}
