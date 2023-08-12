package br.pucminas.products.adapters.web.in.advice;

import br.pucminas.products.adapters.web.config.ObjectMapperConfiguration;
import br.pucminas.products.adapters.web.in.products.CreateProductController;
import br.pucminas.products.adapters.web.in.products.SearchProductController;
import br.pucminas.products.application.domain.Product;
import br.pucminas.products.application.domain.exceptions.ProductNotFoundException;
import br.pucminas.products.application.ports.in.CreateProductUseCase;
import br.pucminas.products.application.ports.in.SearchProductUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static br.pucminas.products.adapters.web.config.ControllerRoutes.PRODUCT_ROUTE;
import static br.pucminas.products.adapters.web.support.ProductDtoSupport.defaultCreateProductDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {CreateProductController.class, SearchProductController.class})
@Import(ObjectMapperConfiguration.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class ExceptionHandlerControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @MockBean
    private CreateProductUseCase createProductUseCase;

    @MockBean
    private SearchProductUseCase searchProductUseCase;


    @Test
    @DisplayName("Given Product When Error Then Return Internal Server Error")
    public void givenProductWhenErrorThenReturnInternalServerError() throws Exception {
        var productDto = defaultCreateProductDto().build();
        var content = this.objectMapper.writeValueAsString(productDto);

        when(this.createProductUseCase.create(any(Product.class))).thenAnswer((Answer<String>) invocationOnMock -> {
            throw new RuntimeException("Error");
        });

        this.mockMvc.perform(post(PRODUCT_ROUTE)
                    .content(content)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Given Product When Is Invalid Then Return UnProcessable Entity")
    public void givenProductWhenIsInvalidThenReturnUnProcessableEntity() throws Exception {
        var productDto = defaultCreateProductDto()
                .withSuggestedTotalMonthlyPremiumAmount(BigDecimal.ONE)
                .build();
        var content = this.objectMapper.writeValueAsString(productDto);


        this.mockMvc.perform(post(PRODUCT_ROUTE)
                        .content(content)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @DisplayName("Given Product When Is Invalid Then Return Bad Request")
    public void givenProductWhenIsInvalidThenReturnBadRequest() throws Exception {
        var productDto = defaultCreateProductDto()
                                .withCategory(null)
                                .build();
        var content = this.objectMapper.writeValueAsString(productDto);


        this.mockMvc.perform(post(PRODUCT_ROUTE)
                    .content(content)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Given Id And Product When Exists Then Return Existing Product")
    public void givenIdAndProductWhenExistsThenReturnExistingProduct() throws Exception {
        var id = 1;

        when(this.searchProductUseCase.findById(anyInt())).thenThrow(new ProductNotFoundException("Product not found"));

        this.mockMvc.perform(get(PRODUCT_ROUTE + "/{id}", id)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isNotFound());
    }

}