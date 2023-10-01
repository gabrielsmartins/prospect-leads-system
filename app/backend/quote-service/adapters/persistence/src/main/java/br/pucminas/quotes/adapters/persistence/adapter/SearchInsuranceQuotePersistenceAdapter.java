package br.pucminas.quotes.adapters.persistence.adapter;

import br.pucminas.quotes.common.stereotype.PersistenceAdapter;
import br.pucminas.quotes.adapters.persistence.adapter.mapper.InsuranceQuotePersistenceMapper;
import br.pucminas.quotes.adapters.persistence.service.InsuranceQuotePersistenceService;
import br.pucminas.quotes.application.domain.InsuranceQuote;
import br.pucminas.quotes.application.ports.out.SearchInsuranceQuotePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static net.logstash.logback.argument.StructuredArguments.kv;
import static net.logstash.logback.marker.Markers.append;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class SearchInsuranceQuotePersistenceAdapter implements SearchInsuranceQuotePort {

    private final InsuranceQuotePersistenceService service;

    @Override
    public Mono<InsuranceQuote> findById(UUID id) {
        log.info("Searching insurance quote by id: {}", kv("id", id));
        return this.service.findById(id)
                           .doOnNext(insuranceQuoteEntity -> log.info(append("insurance_quote", insuranceQuoteEntity), "Insurance quote was found successfully"))
                           .map(InsuranceQuotePersistenceMapper::mapToDomain)
                           .doOnNext(insuranceQuote -> log.info(append("insurance_quote", insuranceQuote), "Insurance quote was mapped successfully"));
    }

}
