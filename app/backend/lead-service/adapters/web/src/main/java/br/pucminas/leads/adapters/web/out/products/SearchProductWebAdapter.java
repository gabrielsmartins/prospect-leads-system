package br.pucminas.leads.adapters.web.out.products;

import br.pucminas.leads.adapters.web.out.products.client.ProductFeignClient;
import br.pucminas.leads.adapters.web.out.products.mapper.ProductWebAdapterMapper;
import br.pucminas.leads.application.domain.Product;
import br.pucminas.leads.application.ports.out.SearchProductPort;
import br.pucminas.leads.common.WebAdapter;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static net.logstash.logback.argument.StructuredArguments.kv;
import static net.logstash.logback.marker.Markers.append;

@WebAdapter
@RequiredArgsConstructor
@Slf4j
public class SearchProductWebAdapter implements SearchProductPort {

    private final ProductFeignClient client;

    @Override
    public Optional<Product> findById(Integer id) {
        try {
            log.info("Searching product id : {}", kv("id", id));
            var response = this.client.findById(id);
            log.info(append("response", response), "Product was found successfully");
            var productDto = response.getBody();
            log.info(append("product", productDto), "Mapping product");
            var product = ProductWebAdapterMapper.mapToDomain(productDto);
            log.info(append("product", product), "Product was mapped successfully");
            return Optional.ofNullable(product);
        } catch (FeignException ex) {
            if (ex.status() == HttpStatus.NOT_FOUND.value()) {
                log.warn("Product not found");
                return Optional.empty();
            }
            throw ex;
        }
    }

}
