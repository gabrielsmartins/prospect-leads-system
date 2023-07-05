package br.pucminas.products.adapters.web.in.products;


import br.pucminas.products.adapters.web.config.ObjectMapperConfiguration;
import br.pucminas.products.application.domain.Product;
import br.pucminas.products.application.ports.in.CreateProductUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static br.pucminas.products.adapters.web.config.ControllerRoutes.PRODUCT_ROUTE;
import static br.pucminas.products.adapters.web.support.ProductDtoSupport.defaultCreateProductDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CreateProductController.class)
@Import(ObjectMapperConfiguration.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CreateProductControllerTest {


    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @MockBean
    private CreateProductUseCase useCase;

    @Test
    @DisplayName("Given Product When Create Then Return Created Product")
    public void givenProductWhenCreateThenReturnCreatedProduct() throws Exception {
        var productDto = defaultCreateProductDto().build();
        var content = this.objectMapper.writeValueAsString(productDto);

        when(this.useCase.create(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        this.mockMvc.perform(post(PRODUCT_ROUTE)
                    .content(content)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isCreated())
                    .andExpect(header().exists(LOCATION));
    }

}