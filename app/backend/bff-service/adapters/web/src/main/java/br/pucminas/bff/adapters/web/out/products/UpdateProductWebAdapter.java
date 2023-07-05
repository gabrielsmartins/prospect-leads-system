package br.pucminas.bff.adapters.web.out.products;

import br.pucminas.bff.adapters.web.out.products.config.ProductWebClientProperties;
import br.pucminas.bff.adapters.web.out.products.dto.UpdateProductDto;
import br.pucminas.bff.adapters.web.out.products.mapper.UpdateProductWebAdapterMapper;
import br.pucminas.bff.application.domain.Product;
import br.pucminas.bff.application.ports.out.UpdateProductPort;
import br.pucminas.bff.common.stereotype.WebAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static net.logstash.logback.marker.Markers.append;

@WebAdapter
@RequiredArgsConstructor
@Slf4j
public class UpdateProductWebAdapter implements UpdateProductPort {

    private final WebClient client;
    private final ProductWebClientProperties properties;

    @Override
    public Mono<Product> update(Integer id, Product product) {
        log.info(append("id", id).and(append("product", product)), "Updating product by id");
        log.info(append("product", product), "Mapping product");
        var productDto = UpdateProductWebAdapterMapper.mapToDto(product);
        log.info(append("product", productDto), "Product was mapped successfully");
        return this.client.patch()
                          .uri(this.properties.getUrl() + "/{id}", id)
                          .accept(MediaType.APPLICATION_JSON)
                          .contentType(MediaType.APPLICATION_JSON)
                          .body(BodyInserters.fromValue(productDto))
                          .retrieve()
                          .bodyToMono(UpdateProductDto.class)
                          .map(UpdateProductWebAdapterMapper::mapToDomain);
    }

}
