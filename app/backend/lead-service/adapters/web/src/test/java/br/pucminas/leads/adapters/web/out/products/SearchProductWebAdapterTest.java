package br.pucminas.leads.adapters.web.out.products;


import br.pucminas.leads.adapters.web.config.feign.FeignConfiguration;
import br.pucminas.leads.adapters.web.config.web.ObjectMapperConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.pucminas.leads.adapters.web.support.ProductDtoSupport.defaultProductDto;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@AutoConfigureWireMock(port = 5000)
@Import({
        SearchProductWebAdapter.class,
        HttpMessageConvertersAutoConfiguration.class,
        FeignAutoConfiguration.class,
        FeignConfiguration.class,
        ObjectMapperConfiguration.class
})
@ActiveProfiles("test")
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SearchProductWebAdapterTest {

    private final SearchProductWebAdapter adapter;
    private final ObjectMapper objectMapper;

    @Test
    @DisplayName("Given Id When Exists Then Return Existing Product")
    public void givenIdWhenExistsThenReturnExistingProduct() throws JsonProcessingException {

        var productDto = defaultProductDto().build();
        var body = this.objectMapper.writeValueAsString(productDto);

        stubFor(get(urlPathMatching("/products/v1/.*"))
                .willReturn(aResponse()
                        .withBody(body)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(HttpStatus.SC_OK)));

        var id = 1;
        var optionalProduct = this.adapter.findById(id);

        assertThat(optionalProduct).isPresent();
    }

    @Test
    @DisplayName("Given Id When Not Exists Then Return Empty")
    public void givenIdWhenNotExistsThenReturnExistingEmpty() throws JsonProcessingException {


        stubFor(get(urlPathMatching("/products/v1/.*"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(HttpStatus.SC_NOT_FOUND)));

        var id = 1;
        var optionalProduct = this.adapter.findById(id);

        assertThat(optionalProduct).isEmpty();
    }

}