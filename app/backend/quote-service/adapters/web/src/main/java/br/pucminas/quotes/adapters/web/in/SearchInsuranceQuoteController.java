package br.pucminas.quotes.adapters.web.in;

import br.pucminas.quotes.adapters.web.in.dto.SearchInsuranceQuoteDto;
import br.pucminas.quotes.adapters.web.in.mapper.SearchProductControllerMapper;
import br.pucminas.quotes.application.ports.in.SearchInsuranceQuoteUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static br.pucminas.quotes.adapters.web.config.ControllerRoutes.INSURANCE_QUOTE_ROUTE;
import static net.logstash.logback.argument.StructuredArguments.kv;
import static net.logstash.logback.marker.Markers.append;

@RestController
@RequestMapping(INSURANCE_QUOTE_ROUTE)
@RequiredArgsConstructor
@Validated
@Slf4j
public class SearchInsuranceQuoteController {

    private final SearchInsuranceQuoteUseCase useCase;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<SearchInsuranceQuoteDto> update(@PathVariable("id") UUID id){
        log.info("Searching insurance quote by id {}", kv("id", id));
        return this.useCase.findById(id)
                           .doOnSuccess(p -> log.info(append("quote", p), "Insurance quote was found successfully"))
                           .doOnError(throwable -> log.error("Error updating insurance quote", throwable))
                           .map(SearchProductControllerMapper::mapToDto);
    }
}
