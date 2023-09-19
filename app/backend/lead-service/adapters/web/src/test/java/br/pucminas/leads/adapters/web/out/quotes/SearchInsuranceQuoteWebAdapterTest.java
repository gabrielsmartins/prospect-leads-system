package br.pucminas.leads.adapters.web.out.quotes;


import br.pucminas.leads.adapters.web.config.feign.FeignConfiguration;
import br.pucminas.leads.adapters.web.config.web.ObjectMapperConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
import wiremock.org.apache.hc.core5.http.HttpStatus;

import static br.pucminas.leads.adapters.web.support.InsuranceQuoteDtoSupport.defaultSearchInsuranceQuoteDto;
import static br.pucminas.leads.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@AutoConfigureWireMock(port = 5000)
@Import({
        SearchInsuranceQuoteWebAdapter.class,
        HttpMessageConvertersAutoConfiguration.class,
        FeignAutoConfiguration.class,
        FeignConfiguration.class,
        ObjectMapperConfiguration.class
})
@ActiveProfiles("test")
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SearchInsuranceQuoteWebAdapterTest {

    private final SearchInsuranceQuoteWebAdapter adapter;
    private final ObjectMapper objectMapper;

    @Test
    @DisplayName("Given Insurance Quote When Exists Then Return Insurance Quote")
    public void givenInsuranceQuoteWhenExistsThenReturnInsuranceQuote() throws JsonProcessingException {

        var quoteDto = defaultSearchInsuranceQuoteDto().build();
        var body = this.objectMapper.writeValueAsString(quoteDto);

        stubFor(get(urlPathMatching("/insurance_quotes/v1/.*"))
                .willReturn(aResponse()
                        .withBody(body)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(HttpStatus.SC_CREATED)));

        var quote = defaultInsuranceQuote().build();

        var optionalInsuranceQuote = this.adapter.findById(quote.getId());
        assertThat(optionalInsuranceQuote).isPresent();
    }

    @Test
    @DisplayName("Given Insurance Quote When Not Exists Then Return Empty")
    public void givenInsuranceQuoteWhenNotExistsThenReturnEmpty() throws JsonProcessingException {

        stubFor(get(urlPathMatching("/insurance_quotes/v1/.*"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(HttpStatus.SC_NOT_FOUND)));

        var quote = defaultInsuranceQuote().build();

        var optionalInsuranceQuote = this.adapter.findById(quote.getId());
        assertThat(optionalInsuranceQuote).isEmpty();
    }
}