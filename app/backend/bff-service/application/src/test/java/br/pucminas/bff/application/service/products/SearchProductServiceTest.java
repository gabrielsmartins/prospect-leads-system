package br.pucminas.bff.application.service.products;

import br.pucminas.bff.application.ports.out.products.SearchProductPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static br.pucminas.bff.application.support.ProductSupport.defaultProduct;
import static org.mockito.Mockito.*;

class SearchProductServiceTest {

    private SearchProductService service;
    private SearchProductPort port;

    @BeforeEach
    public void setup() {
        this.port = mock(SearchProductPort.class);
        this.service = new SearchProductService(this.port);
    }

    @Test
    @DisplayName("Given Id When Exists Then Existing Product")
    public void givenIdWhenExistsThenExistingProduct() {
        var id = 1;

        var product = defaultProduct().build();

        when(this.port.findById(id)).thenReturn(Mono.just(product));

        this.service.findById(id)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();

        verify(this.port, times(1)).findById(id);
    }

    @Test
    @DisplayName("Given Pageable When Exist Then Products")
    public void givenPageableWhenExistThenProducts() {
        var id = 1;

        var product = defaultProduct().build();

        var pageRequest = PageRequest.of(0 , 30);
        when(this.port.findAll(any(Pageable.class))).thenReturn(Mono.just(new PageImpl<>(List.of(product), pageRequest, 1)));

        this.service.findAll(pageRequest)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();

        verify(this.port, times(1)).findAll(pageRequest);
    }

}