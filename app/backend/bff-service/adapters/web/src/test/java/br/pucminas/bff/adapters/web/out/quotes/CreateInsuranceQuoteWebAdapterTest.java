package br.pucminas.bff.adapters.web.out.quotes;


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

import static br.pucminas.bff.adapters.web.out.support.InsuranceQuoteDtoSupport.defaultCreateInsuranceQuoteDto;
import static br.pucminas.bff.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@AutoConfigureWireMock(port = 5000)
@Import({
        CreateInsuranceQuoteWebAdapter.class,
        ObjectMapperConfiguration.class,
        WebClientConfiguration.class,
})
@ActiveProfiles("test")
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
class CreateInsuranceQuoteWebAdapterTest {

    private final CreateInsuranceQuoteWebAdapter adapter;
    private final ObjectMapper objectMapper;

    @Test
    @DisplayName("Given Insurance Quote When Create Then Return Created Insurance Quote")
    public void givenInsuranceQuoteWhenCreateThenReturnCreatedInsuranceQuote() throws JsonProcessingException {

        var quoteDto = defaultCreateInsuranceQuoteDto().build();
        var body = this.objectMapper.writeValueAsString(quoteDto);

        stubFor(post(urlPathMatching("/insurance_quotes/v1"))
                .willReturn(aResponse()
                        .withBody(body)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(HttpStatus.SC_CREATED)));

        var quote = defaultInsuranceQuote().build();

        this.adapter.create(quote)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();
    }


}