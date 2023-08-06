package br.pucminas.bff.adapters.web.out.quotes.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "webservice.quotes")
@Getter
@Setter
@ToString
public class InsuranceQuoteWebClientProperties {

    private String url;

}
