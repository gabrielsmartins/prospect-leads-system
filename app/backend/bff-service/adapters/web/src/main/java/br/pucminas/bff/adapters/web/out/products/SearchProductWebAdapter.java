package br.pucminas.bff.adapters.web.out.products;

import br.pucminas.bff.adapters.web.out.products.config.ProductWebClientProperties;
import br.pucminas.bff.adapters.web.out.products.dto.SearchProductDto;
import br.pucminas.bff.adapters.web.out.products.mapper.SearchProductWebAdapterMapper;
import br.pucminas.bff.application.domain.Product;
import br.pucminas.bff.application.ports.out.products.SearchProductPort;
import br.pucminas.bff.common.stereotype.WebAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static net.logstash.logback.argument.StructuredArguments.kv;

@WebAdapter
@RequiredArgsConstructor
@EnableConfigurationProperties(ProductWebClientProperties.class)
@Slf4j
public class SearchProductWebAdapter implements SearchProductPort {

    private final WebClient client;
    private final ProductWebClientProperties properties;

    @Override
    public Mono<Product> findById(Integer id) {
        log.info("Searching product by id {}", kv("id", id));
        return this.client.get()
                          .uri(this.properties.getUrl() + "/{id}", id)
                          .accept(MediaType.APPLICATION_JSON)
                          .retrieve()
                          .bodyToMono(SearchProductDto.class)
                          .map(SearchProductWebAdapterMapper::mapToDomain);
    }

}
