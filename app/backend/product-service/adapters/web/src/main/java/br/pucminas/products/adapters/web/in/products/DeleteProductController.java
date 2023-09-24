package br.pucminas.products.adapters.web.in.products;

import br.pucminas.products.adapters.web.in.products.dto.CreateProductDto;
import br.pucminas.products.application.ports.in.DeleteProductUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.pucminas.products.adapters.web.config.ControllerRoutes.PRODUCT_ROUTE;
import static net.logstash.logback.marker.Markers.append;

@RestController
@RequestMapping(PRODUCT_ROUTE)
@RequiredArgsConstructor
@Slf4j
public class DeleteProductController {

    private final DeleteProductUseCase useCase;

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateProductDto> delete(@PathVariable("id") Integer id) {
        log.info(append("id", id), "Deleting product by id");
        this.useCase.delete(id);
        log.info(append("id", id), "Product was deleted by id successfully");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
