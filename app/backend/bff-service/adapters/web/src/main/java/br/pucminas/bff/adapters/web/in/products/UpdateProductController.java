package br.pucminas.bff.adapters.web.in.products;

import br.pucminas.bff.adapters.web.in.products.dto.UpdateProductDto;
import br.pucminas.bff.adapters.web.in.products.mapper.UpdateProductControllerMapper;
import br.pucminas.bff.application.ports.in.UpdateProductUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static br.pucminas.bff.adapters.web.config.ControllerRoutes.PRODUCTS_ROUTE;
import static net.logstash.logback.argument.StructuredArguments.kv;
import static net.logstash.logback.marker.Markers.append;

@RestController
@RequestMapping(PRODUCTS_ROUTE)
@RequiredArgsConstructor
@Validated
@Slf4j
public class UpdateProductController {

    private final UpdateProductUseCase useCase;

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<UpdateProductDto> update(@PathVariable("id") Integer id, @RequestBody @Valid UpdateProductDto productDto){
        log.info(append("product", productDto), "Mapping product");
        var product = UpdateProductControllerMapper.mapToDomain(productDto);
        log.info(append("product", product), "Product was mapped successfully");
        log.info(append("product", product), "Updating product by id {}", kv("id", id));
        return this.useCase.update(id, product)
                           .doOnSuccess(p -> log.info(append("product", p), "Product was updated successfully"))
                           .doOnError(throwable -> log.error("Error updating product", throwable))
                           .map(UpdateProductControllerMapper::mapToDto);
    }

}
