package br.pucminas.bff.adapters.web.out.products;

import br.pucminas.bff.adapters.web.out.products.config.ProductWebClientProperties;
import br.pucminas.bff.adapters.web.out.products.dto.CreateProductDto;
import br.pucminas.bff.adapters.web.out.products.mapper.CreateProductWebAdapterMapper;
import br.pucminas.bff.application.domain.Product;
import br.pucminas.bff.application.ports.out.products.CreateProductPort;
import br.pucminas.bff.common.stereotype.WebAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static net.logstash.logback.marker.Markers.append;

@WebAdapter
@RequiredArgsConstructor
@EnableConfigurationProperties(ProductWebClientProperties.class)
@Slf4j
public class CreateProductWebAdapter implements CreateProductPort {

    private final WebClient client;
    private final ProductWebClientProperties properties;

    @Override
    public Mono<Product> create(Product product) {
        log.info(append("product", product), "Mapping product");
        var productDto = CreateProductWebAdapterMapper.mapToDto(product);
        log.info(append("product", productDto), "Product was mapped successfully");
        return this.client.post()
                          .uri(this.properties.getUrl())
                          .accept(MediaType.APPLICATION_JSON)
                          .contentType(MediaType.APPLICATION_JSON)
                          .body(BodyInserters.fromValue(productDto))
                          .retrieve()
                          .bodyToMono(CreateProductDto.class)
                          .map(CreateProductWebAdapterMapper::mapToDomain);
    }

}
