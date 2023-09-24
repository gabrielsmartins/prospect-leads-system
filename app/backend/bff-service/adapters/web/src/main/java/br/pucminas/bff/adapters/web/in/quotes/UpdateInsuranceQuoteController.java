package br.pucminas.bff.adapters.web.in.quotes;


import br.pucminas.bff.adapters.web.in.quotes.dto.UpdateInsuranceQuoteDto;
import br.pucminas.bff.adapters.web.in.quotes.mapper.UpdateInsuranceQuoteControllerMapper;
import br.pucminas.bff.application.ports.in.quotes.UpdateInsuranceQuoteUseCase;
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
import java.util.UUID;

import static br.pucminas.bff.adapters.web.config.ControllerRoutes.INSURANCE_QUOTES_ROUTE;
import static net.logstash.logback.argument.StructuredArguments.kv;
import static net.logstash.logback.marker.Markers.append;

@RestController
@RequestMapping(INSURANCE_QUOTES_ROUTE)
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(name = "insurance quotes", description = "Insurance Quotes API")
public class UpdateInsuranceQuoteController {

    private final UpdateInsuranceQuoteUseCase useCase;

    @Operation(description = "Update Insurance Quote")
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<UpdateInsuranceQuoteDto> update(@PathVariable("id") UUID id, @RequestBody @Valid UpdateInsuranceQuoteDto quoteDto){
        log.info(append("quote", quoteDto), "Mapping insurance quote");
        var quote = UpdateInsuranceQuoteControllerMapper.mapToDomain(quoteDto);
        log.info(append("quote", quote), "Insurance quote was mapped successfully");
        log.info(append("quote", quote), "Updating insurance quote by id {}", kv("id", id));
        return this.useCase.update(id, quote)
                           .doOnSuccess(p -> log.info(append("quote", p), "Insurance quote was updated successfully"))
                           .doOnError(throwable -> log.error("Error updating quote", throwable))
                           .map(UpdateInsuranceQuoteControllerMapper::mapToDto);
    }

}
