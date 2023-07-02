package br.pucminas.products.application.service;


import br.pucminas.products.application.ports.out.SaveProductPort;
import br.pucminas.products.application.support.ProductSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
 class CreateProductServiceTest {

    private CreateProductService service;
    private SaveProductPort port;

    @BeforeEach
    public void setup() {
        this.port = mock(SaveProductPort.class);
        this.service = new CreateProductService(this.port);
    }

    @Test
    @DisplayName("Given Product When Create Then Return Created Product")
    public void givenProductWhenCreateThenReturnCreatedProduct() {
        var product = ProductSupport.defaultProduct()
                .withActive(false)
                .build();
        when(this.port.save(product)).thenAnswer(invocation -> invocation.getArgument(0));
        var createdProduct = this.service.create(product);
        assertThat(createdProduct.isActive()).isTrue();
        assertThat(createdProduct).isNotNull();
    }

}