package br.pucminas.bff.adapters.web.in.products;

import br.pucminas.bff.adapters.web.in.products.dto.CreateProductDto;
import br.pucminas.bff.adapters.web.in.products.mapper.CreateProductControllerMapper;
import br.pucminas.bff.application.ports.in.products.CreateProductUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static br.pucminas.bff.adapters.web.config.ControllerRoutes.PRODUCTS_ROUTE;
import static net.logstash.logback.marker.Markers.append;

@RestController
@RequestMapping(PRODUCTS_ROUTE)
@RequiredArgsConstructor
@Validated
@Slf4j
public class CreateProductController {

    private final CreateProductUseCase useCase;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CreateProductDto> create(@RequestBody @Valid CreateProductDto productDto){
        log.info(append("product", productDto), "Mapping product");
        var product = CreateProductControllerMapper.mapToDomain(productDto);
        log.info(append("product", product), "Product was mapped successfully");
        log.info(append("product", product), "Creating product");
        return this.useCase.create(product)
                           .doOnSuccess(p -> log.info(append("product", p), "Product was created successfully"))
                           .doOnError(throwable -> log.error("Error saving product", throwable))
                           .map(CreateProductControllerMapper::mapToDto);
    }

}
