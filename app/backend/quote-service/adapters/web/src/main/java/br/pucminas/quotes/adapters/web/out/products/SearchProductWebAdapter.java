package br.pucminas.quotes.adapters.web.out.products;

import br.pucminas.quotes.common.stereotype.WebAdapter;
import br.pucminas.quotes.adapters.web.out.products.config.ProductWebClientProperties;
import br.pucminas.quotes.adapters.web.out.products.dto.ProductDto;
import br.pucminas.quotes.adapters.web.out.products.mapper.ProductWebAdapterMapper;
import br.pucminas.quotes.application.domain.Product;
import br.pucminas.quotes.application.ports.out.SearchProductPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static net.logstash.logback.argument.StructuredArguments.kv;

@WebAdapter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@EnableConfigurationProperties(ProductWebClientProperties.class)
@Slf4j
public class SearchProductWebAdapter implements SearchProductPort {

    private final WebClient client;
    private final ProductWebClientProperties properties;

    @Override
    public Mono<Product> findById(Integer id) {
        log.info("Searching product by id: {}", kv("id", id));
        return this.client.get()
                          .uri(this.properties.getUrl() + "/{id}", id)
                          .accept(MediaType.APPLICATION_JSON)
                          .retrieve()
                          .bodyToMono(ProductDto.class)
                          .map(ProductWebAdapterMapper::mapToDomain);
    }

}
