package br.pucminas.products.adapters.web.in.products;

import br.pucminas.products.adapters.web.in.products.dto.PageDto;
import br.pucminas.products.adapters.web.in.products.dto.SearchProductDto;
import br.pucminas.products.adapters.web.in.products.mapper.SearchProductControllerMapper;
import br.pucminas.products.application.ports.in.SearchProductUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
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

    @GetMapping()
    public ResponseEntity<PageDto<SearchProductDto>> findAll(Pageable pageable) {
        log.info(append("pageable", pageable), "Searching all products");
        var page = this.useCase.findAll(pageable);
        log.info(append("page", page), "Product were found successfully");

        log.info(append("page", page), "Mapping product page");
        var pageDto = SearchProductControllerMapper.mapToDto(page);
        log.info(append("page", pageDto), "Product page was mapped successfully");
        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

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
