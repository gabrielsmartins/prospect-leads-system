package br.pucminas.bff.adapters.web.out.products;

import br.pucminas.bff.adapters.web.out.products.config.ProductWebClientProperties;
import br.pucminas.bff.adapters.web.out.products.dto.PageDto;
import br.pucminas.bff.adapters.web.out.products.dto.SearchProductDto;
import br.pucminas.bff.adapters.web.out.products.mapper.SearchProductWebAdapterMapper;
import br.pucminas.bff.application.domain.Product;
import br.pucminas.bff.application.ports.out.products.SearchProductPort;
import br.pucminas.bff.common.stereotype.WebAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import static net.logstash.logback.argument.StructuredArguments.kv;
import static net.logstash.logback.marker.Markers.append;

@WebAdapter
@RequiredArgsConstructor
@EnableConfigurationProperties(ProductWebClientProperties.class)
@Slf4j
public class SearchProductWebAdapter implements SearchProductPort {

    private final WebClient client;
    private final ProductWebClientProperties properties;

    @Override
    public Mono<Page<Product>> findAll(Pageable pageable) {
        log.info(append("pageable", pageable), "Searching all products");
        var builder = UriComponentsBuilder.fromUriString(this.properties.getUrl())
                                          .queryParam("page", pageable.getPageNumber())
                                          .queryParam("page_size", pageable.getPageSize());
        return this.client.get()
                          .uri(builder.build().toUri())
                          .accept(MediaType.APPLICATION_JSON)
                          .retrieve()
                          .bodyToMono(new ParameterizedTypeReference<PageDto<SearchProductDto>>() {})
                          .map(SearchProductWebAdapterMapper::mapToDomain);
    }

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
