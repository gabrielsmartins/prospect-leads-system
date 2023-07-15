package br.pucminas.bff.adapters.web.out.products;

import br.pucminas.bff.adapters.web.config.ObjectMapperConfiguration;
import br.pucminas.bff.adapters.web.config.WebClientConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;
import wiremock.org.apache.hc.core5.http.HttpStatus;

import static br.pucminas.bff.adapters.web.out.support.ProductDtoSupport.defaultUpdateProductDto;
import static br.pucminas.bff.application.support.ProductSupport.defaultProduct;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@AutoConfigureWireMock(port = 5000)
@Import({
        UpdateProductWebAdapter.class,
        ObjectMapperConfiguration.class,
        WebClientConfiguration.class
})
@ActiveProfiles("test")
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
class UpdateProductWebAdapterTest {

    private final UpdateProductWebAdapter adapter;
    private final ObjectMapper objectMapper;

    @Test
    @DisplayName("Given Id And Product When Update Then Return Updated Product")
    public void givenIdAndProductWhenUpdateThenReturnUpdatedProduct() throws JsonProcessingException {

        var productDto = defaultUpdateProductDto().build();
        var body = this.objectMapper.writeValueAsString(productDto);

        stubFor(put(urlPathMatching("/products/v1/."))
                .willReturn(aResponse()
                        .withBody(body)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(HttpStatus.SC_OK)));

        var id = 1;
        var product = defaultProduct().build();
        this.adapter.update(id, product)
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }

}