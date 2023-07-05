package br.pucminas.products.adapters.web.in.products;

import br.pucminas.products.adapters.web.in.products.dto.CreateProductDto;
import br.pucminas.products.adapters.web.in.products.mapper.CreateProductControllerMapper;
import br.pucminas.products.application.ports.in.CreateProductUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import static br.pucminas.products.adapters.web.config.ControllerRoutes.PRODUCT_ROUTE;
import static net.logstash.logback.marker.Markers.append;

@RestController
@RequestMapping(PRODUCT_ROUTE)
@RequiredArgsConstructor
@Slf4j
@Validated
public class CreateProductController {

    private final CreateProductUseCase useCase;

    @PostMapping
    public ResponseEntity<CreateProductDto> create(@RequestBody @Valid CreateProductDto productDto) {
        log.info(append("product", productDto), "Mapping product");
        var product = CreateProductControllerMapper.mapToDomain(productDto);
        log.info(append("product", product), "Product was mapped successfully");

        log.info(append("product", product), "Creating product");
        var createdProduct = this.useCase.create(product);
        log.info(append("product", createdProduct), "Product was created successfully");

        log.info(append("product", createdProduct), "Mapping created product");
        var createdProductDto = CreateProductControllerMapper.mapToDto(createdProduct);
        log.info(append("product", createdProductDto), "Created product was mapped successfully");

        var uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/{id}").buildAndExpand(createdProductDto.getId()).toUri();

        return ResponseEntity.created(uri).body(createdProductDto);
    }

}
