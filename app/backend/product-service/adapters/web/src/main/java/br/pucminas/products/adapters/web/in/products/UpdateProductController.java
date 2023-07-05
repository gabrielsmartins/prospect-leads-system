package br.pucminas.products.adapters.web.in.products;

import br.pucminas.products.adapters.web.config.ControllerRoutes;
import br.pucminas.products.adapters.web.in.products.dto.UpdateProductDto;
import br.pucminas.products.adapters.web.in.products.mapper.UpdateProductControllerMapper;
import br.pucminas.products.application.ports.in.UpdateProductUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static net.logstash.logback.marker.Markers.append;

@RestController
@RequestMapping(ControllerRoutes.PRODUCT_ROUTE)
@RequiredArgsConstructor
@Slf4j
@Validated
public class UpdateProductController {

    private final UpdateProductUseCase useCase;

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateProductDto> update(@PathVariable("id") Integer id, @RequestBody @Valid UpdateProductDto productDto) {
        log.info(append("id", id), "Updating product by id");

        log.info(append("product", productDto), "Mapping product");
        var product = UpdateProductControllerMapper.mapToDomain(productDto);
        log.info(append("product", product), "Product was mapped successfully");

        log.info(append("id", id).and(append("product", product)), "Updating product by id");
        var updatedProduct = this.useCase.update(id, product);
        log.info(append("product", updatedProduct), "Product was updated successfully");

        log.info(append("product", updatedProduct), "Mapping updated product");
        var createdProductDto = UpdateProductControllerMapper.mapToDto(updatedProduct);
        log.info(append("product", createdProductDto), "Updated product was mapped successfully");
        return new ResponseEntity<>(createdProductDto, HttpStatus.OK);
    }

}
