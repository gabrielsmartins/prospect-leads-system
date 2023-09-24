package br.pucminas.bff.adapters.web.in.quotes;

import br.pucminas.bff.adapters.web.in.quotes.dto.CreateInsuranceQuoteDto;
import br.pucminas.bff.adapters.web.in.quotes.mapper.CreateInsuranceQuoteControllerMapper;
import br.pucminas.bff.application.ports.in.quotes.CreateInsuranceQuoteUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static br.pucminas.bff.adapters.web.config.ControllerRoutes.INSURANCE_QUOTES_ROUTE;
import static net.logstash.logback.marker.Markers.append;

@RestController
@RequestMapping(INSURANCE_QUOTES_ROUTE)
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(name = "insurance quotes", description = "Insurance Quotes API")
public class CreateInsuranceQuoteController {

    private final CreateInsuranceQuoteUseCase useCase;

    @Operation(description = "Create Insurance Quote")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CreateInsuranceQuoteDto> create(@RequestBody @Valid CreateInsuranceQuoteDto quoteDto){
        log.info(append("quote", quoteDto), "Mapping insurance quote");
        var quote = CreateInsuranceQuoteControllerMapper.mapToDomain(quoteDto);
        log.info(append("quote", quote), "Insurance quote was mapped successfully");
        log.info(append("quote", quote), "Creating insurance quote");
        return this.useCase.create(quote)
                           .doOnSuccess(p -> log.info(append("quote", p), "Insurance quote was created successfully"))
                           .doOnError(throwable -> log.error("Error saving insurance quote", throwable))
                           .map(CreateInsuranceQuoteControllerMapper::mapToDto);
    }

}
