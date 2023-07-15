package br.pucminas.quotes.adapters.persistence.adapter;

import br.pucminas.bff.common.stereotype.PersistenceAdapter;
import br.pucminas.quotes.adapters.persistence.adapter.mapper.InsuranceQuotePersistenceMapper;
import br.pucminas.quotes.adapters.persistence.service.InsuranceQuotePersistenceService;
import br.pucminas.quotes.application.domain.InsuranceQuote;
import br.pucminas.quotes.application.ports.out.SaveInsuranceQuotePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import static net.logstash.logback.marker.Markers.append;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class SaveInsuranceQuotePersistenceAdapter implements SaveInsuranceQuotePort {

    private final InsuranceQuotePersistenceService service;

    @Override
    public Mono<InsuranceQuote> save(InsuranceQuote insuranceQuote) {
        log.info(append("insurance_quote", insuranceQuote), "Mapping insurance quote");
        var insuranceQuoteEntity = InsuranceQuotePersistenceMapper.mapToEntity(insuranceQuote);
        log.info(append("insurance_quote", insuranceQuoteEntity), "Insurance quote was mapped successfully");
        return this.service.save(insuranceQuoteEntity)
                           .doOnNext(savedInsuranceQuoteEntity -> log.info(append("insurance_quote", savedInsuranceQuoteEntity), "Insurance quote was saved successfully"))
                           .map(InsuranceQuotePersistenceMapper::mapToDomain)
                           .doOnNext(savedInsuranceQuote -> log.info(append("insurance_quote", savedInsuranceQuote), "Saved insurance quote was mapped successfully"));
    }

}
