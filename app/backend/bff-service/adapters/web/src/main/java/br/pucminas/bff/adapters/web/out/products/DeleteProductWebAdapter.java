package br.pucminas.bff.adapters.web.out.products;

import br.pucminas.bff.adapters.web.out.products.config.ProductWebClientProperties;
import br.pucminas.bff.application.ports.out.products.DeleteProductPort;
import br.pucminas.bff.common.stereotype.WebAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@WebAdapter
@RequiredArgsConstructor
@EnableConfigurationProperties(ProductWebClientProperties.class)
@Slf4j
public class DeleteProductWebAdapter implements DeleteProductPort {

    private final WebClient client;
    private final ProductWebClientProperties properties;

    @Override
    public Mono<Void> delete(Integer id) {
        log.info("Deleting product by id {}", id);
        return this.client.delete()
                          .uri(this.properties.getUrl() + "/{id}", id)
                          .accept(MediaType.APPLICATION_JSON)
                          .retrieve()
                          .bodyToMono(String.class)
                          .map(o -> Mono.empty())
                          .then();
    }

}
