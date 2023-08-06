package br.pucminas.bff.adapters.web.out.quotes;

import br.pucminas.bff.adapters.web.out.quotes.config.InsuranceQuoteWebClientProperties;
import br.pucminas.bff.adapters.web.out.quotes.dto.SearchInsuranceQuoteDto;
import br.pucminas.bff.adapters.web.out.quotes.mapper.SearchInsuranceQuoteWebAdapterMapper;
import br.pucminas.bff.application.domain.InsuranceQuote;
import br.pucminas.bff.application.ports.out.quotes.SearchInsuranceQuotePort;
import br.pucminas.bff.common.stereotype.WebAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static net.logstash.logback.argument.StructuredArguments.kv;

@WebAdapter
@RequiredArgsConstructor
@EnableConfigurationProperties(InsuranceQuoteWebClientProperties.class)
@Slf4j
public class SearchInsuranceQuoteWebAdapter implements SearchInsuranceQuotePort {

    private final WebClient client;
    private final InsuranceQuoteWebClientProperties properties;

    @Override
    public Mono<InsuranceQuote> findById(UUID id) {
        log.info("Searching insurance quote by id {}", kv("id", id));
        return this.client.get()
                          .uri(this.properties.getUrl() + "/{id}", id)
                          .accept(MediaType.APPLICATION_JSON)
                          .retrieve()
                          .bodyToMono(SearchInsuranceQuoteDto.class)
                          .map(SearchInsuranceQuoteWebAdapterMapper::mapToDomain);
    }

}
