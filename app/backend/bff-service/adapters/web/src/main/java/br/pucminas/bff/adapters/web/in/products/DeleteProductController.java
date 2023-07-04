package br.pucminas.bff.adapters.web.in.products;

import br.pucminas.bff.application.ports.in.DeleteProductUseCase;
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
public class DeleteProductController {

    private final DeleteProductUseCase useCase;

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> update(@PathVariable("id") Integer id){
        log.info("Deleting product by id {}", kv("id", id));
        return this.useCase.delete(id)
                           .doOnSuccess(p -> log.info(append("product", p), "Product was updated successfully"))
                           .doOnError(throwable -> log.error("Error updating product", throwable));
    }
}
