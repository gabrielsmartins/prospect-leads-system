package br.pucminas.leads.adapters.web.out.quotes;

import br.pucminas.leads.adapters.web.out.quotes.client.InsuranceQuoteFeignClient;
import br.pucminas.leads.adapters.web.out.quotes.mapper.InsuranceQuoteWebMapper;
import br.pucminas.leads.application.domain.InsuranceQuote;
import br.pucminas.leads.application.ports.out.SearchInsuranceQuotePort;
import br.pucminas.leads.common.stereotype.WebAdapter;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.UUID;

import static net.logstash.logback.argument.StructuredArguments.kv;
import static net.logstash.logback.marker.Markers.append;

@WebAdapter
@RequiredArgsConstructor
@Slf4j
public class SearchInsuranceQuoteWebAdapter implements SearchInsuranceQuotePort {

    private final InsuranceQuoteFeignClient client;

    @Override
    public Optional<InsuranceQuote> findById(UUID id) {
        try {
            log.info("Searching insurance quote id : {}", kv("id", id));
            var response = this.client.findById(id);
            log.info(append("response", response), "Insurance quote was found successfully");
            var insuranceQuoteDto = response.getBody();
            log.info(append("insurance_quote", insuranceQuoteDto), "Mapping insurance quote");
            var insuranceQuote = InsuranceQuoteWebMapper.mapToDomain(insuranceQuoteDto);
            log.info(append("insurance_quote", insuranceQuote), "Insurance quote was mapped successfully");
            return Optional.ofNullable(insuranceQuote);
        } catch (FeignException ex) {
            if (ex.status() == HttpStatus.NOT_FOUND.value()) {
                log.warn("Insurance quote not found");
                return Optional.empty();
            }
            throw ex;
        }
    }

}
