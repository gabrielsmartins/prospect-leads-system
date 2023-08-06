package br.pucminas.bff.adapters.web.out.quotes;

import br.pucminas.bff.adapters.web.out.quotes.config.InsuranceQuoteWebClientProperties;
import br.pucminas.bff.adapters.web.out.quotes.dto.CreateInsuranceQuoteDto;
import br.pucminas.bff.adapters.web.out.quotes.mapper.CreateInsuranceQuoteWebAdapterMapper;
import br.pucminas.bff.application.domain.InsuranceQuote;
import br.pucminas.bff.application.ports.out.quotes.CreateInsuranceQuotePort;
import br.pucminas.bff.common.stereotype.WebAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static net.logstash.logback.marker.Markers.append;

@WebAdapter
@RequiredArgsConstructor
@EnableConfigurationProperties(InsuranceQuoteWebClientProperties.class)
@Slf4j
public class CreateInsuranceQuoteWebAdapter implements CreateInsuranceQuotePort {

    private final WebClient client;
    private final InsuranceQuoteWebClientProperties properties;

    @Override
    public Mono<InsuranceQuote> create(InsuranceQuote quote) {
        log.info(append("quote", quote), "Mapping quote");
        var insuranceQuoteDto = CreateInsuranceQuoteWebAdapterMapper.mapToDto(quote);
        log.info(append("quote", insuranceQuoteDto), "Quote was mapped successfully");
        return this.client.post()
                          .uri(this.properties.getUrl())
                          .accept(MediaType.APPLICATION_JSON)
                          .contentType(MediaType.APPLICATION_JSON)
                          .body(BodyInserters.fromValue(insuranceQuoteDto))
                          .retrieve()
                          .bodyToMono(CreateInsuranceQuoteDto.class)
                          .map(CreateInsuranceQuoteWebAdapterMapper::mapToDomain);
    }

}
