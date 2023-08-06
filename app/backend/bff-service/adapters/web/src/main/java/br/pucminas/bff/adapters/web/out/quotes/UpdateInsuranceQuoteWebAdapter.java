package br.pucminas.bff.adapters.web.out.quotes;

import br.pucminas.bff.adapters.web.out.quotes.config.InsuranceQuoteWebClientProperties;
import br.pucminas.bff.adapters.web.out.quotes.dto.UpdateInsuranceQuoteDto;
import br.pucminas.bff.adapters.web.out.quotes.mapper.UpdateInsuranceQuoteWebAdapterMapper;
import br.pucminas.bff.application.domain.InsuranceQuote;
import br.pucminas.bff.application.ports.out.quotes.UpdateInsuranceQuotePort;
import br.pucminas.bff.common.stereotype.WebAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static net.logstash.logback.marker.Markers.append;

@WebAdapter
@RequiredArgsConstructor
@EnableConfigurationProperties(InsuranceQuoteWebClientProperties.class)
@Slf4j
public class UpdateInsuranceQuoteWebAdapter implements UpdateInsuranceQuotePort {

    private final WebClient client;
    private final InsuranceQuoteWebClientProperties properties;

    @Override
    public Mono<InsuranceQuote> update(UUID id, InsuranceQuote quote) {
        log.info(append("quote", quote), "Mapping quote");
        var insuranceQuoteDto = UpdateInsuranceQuoteWebAdapterMapper.mapToDto(quote);
        log.info(append("quote", insuranceQuoteDto), "Quote was mapped successfully");
        return this.client.patch()
                          .uri(this.properties.getUrl() + "/{id}", id)
                          .accept(MediaType.APPLICATION_JSON)
                          .contentType(MediaType.APPLICATION_JSON)
                          .body(BodyInserters.fromValue(insuranceQuoteDto))
                          .retrieve()
                          .bodyToMono(UpdateInsuranceQuoteDto.class)
                          .map(UpdateInsuranceQuoteWebAdapterMapper::mapToDomain);
    }

}
