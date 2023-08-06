package br.pucminas.bff.application.service.quotes;


import br.pucminas.bff.application.ports.out.leads.CaptureLeadPort;
import br.pucminas.bff.application.ports.out.quotes.CreateInsuranceQuotePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static br.pucminas.bff.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static org.mockito.Mockito.*;

class CreateInsuranceQuoteServiceTest {

    private CreateInsuranceQuoteService service;
    private CreateInsuranceQuotePort createInsuranceQuotePort;
    private CaptureLeadPort captureLeadPort;

    @BeforeEach
    public void setup() {
        this.createInsuranceQuotePort = mock(CreateInsuranceQuotePort.class);
        this.captureLeadPort = mock(CaptureLeadPort.class);
        this.service = new CreateInsuranceQuoteService(this.createInsuranceQuotePort, this.captureLeadPort);
    }

    @Test
    @DisplayName("Given Insurance Quote When Create Then Return Created Insurance Quote And Capture Lead")
    public void givenInsuranceQuoteWhenCreateThenReturnCreatedInsuranceQuote() {
        var insuranceQuote = defaultInsuranceQuote().build();
        when(this.createInsuranceQuotePort.create(insuranceQuote)).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));
        when(this.captureLeadPort.capture(any(UUID.class))).thenReturn(Mono.empty());

        this.service.create(insuranceQuote)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();

        verify(this.captureLeadPort, times(1)).capture(any(UUID.class));
    }

}