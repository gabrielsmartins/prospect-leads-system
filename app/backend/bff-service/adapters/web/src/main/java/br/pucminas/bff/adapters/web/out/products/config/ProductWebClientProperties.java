package br.pucminas.bff.adapters.web.out.products.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "webservice.products")
@Getter
@Setter
@ToString
public class ProductWebClientProperties {

    private String url;

}
