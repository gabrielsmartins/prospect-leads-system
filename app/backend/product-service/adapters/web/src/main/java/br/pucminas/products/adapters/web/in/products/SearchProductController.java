package br.pucminas.products.adapters.web.in.products;

import br.pucminas.products.adapters.web.in.products.dto.SearchProductDto;
import br.pucminas.products.adapters.web.in.products.mapper.SearchProductControllerMapper;
import br.pucminas.products.application.ports.in.SearchProductUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.pucminas.products.adapters.web.config.ControllerRoutes.PRODUCT_ROUTE;
import static net.logstash.logback.marker.Markers.append;

@RestController
@RequestMapping(PRODUCT_ROUTE)
@RequiredArgsConstructor
@Slf4j
public class SearchProductController {

    private final SearchProductUseCase useCase;

    @GetMapping("/{id}")
    public ResponseEntity<SearchProductDto> findById(@PathVariable("id") Integer id) {
        log.info(append("id", id), "Searching product by id");
        var product = this.useCase.findById(id);
        log.info(append("product", product), "Product was found successfully");

        log.info(append("product", product), "Mapping product");
        var productDto = SearchProductControllerMapper.mapToDto(product);
        log.info(append("product", productDto), "Product was mapped successfully");
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

}
