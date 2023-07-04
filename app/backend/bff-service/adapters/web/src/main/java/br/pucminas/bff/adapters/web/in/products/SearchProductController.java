package br.pucminas.bff.adapters.web.in.products;

import br.pucminas.bff.adapters.web.in.products.dto.SearchProductDto;
import br.pucminas.bff.adapters.web.in.products.mapper.SearchProductControllerMapper;
import br.pucminas.bff.application.ports.in.SearchProductUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static br.pucminas.bff.adapters.web.config.Routes.PRODUCT_ROUTE;
import static net.logstash.logback.argument.StructuredArguments.kv;
import static net.logstash.logback.marker.Markers.append;

@RestController
@RequestMapping(PRODUCT_ROUTE)
@RequiredArgsConstructor
@Validated
@Slf4j
public class SearchProductController {

    private final SearchProductUseCase useCase;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<SearchProductDto> update(@PathVariable("id") Integer id){
        log.info("Searching product by id {}", kv("id", id));
        return this.useCase.findById(id)
                           .doOnSuccess(p -> log.info(append("product", p), "Product was found successfully"))
                           .doOnError(throwable -> log.error("Error updating product", throwable))
                           .map(SearchProductControllerMapper::mapToDto);
    }
}
