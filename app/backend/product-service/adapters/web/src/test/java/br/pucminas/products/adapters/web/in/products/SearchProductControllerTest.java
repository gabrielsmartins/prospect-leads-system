package br.pucminas.products.adapters.web.in.products;

import br.pucminas.products.adapters.web.config.ObjectMapperConfiguration;
import br.pucminas.products.application.ports.in.SearchProductUseCase;
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
import static br.pucminas.products.application.support.ProductSupport.defaultProduct;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(SearchProductController.class)
@Import(ObjectMapperConfiguration.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class SearchProductControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    private SearchProductUseCase useCase;

    @Test
    @DisplayName("Given Id And Product When Exists Then Return Existing Product")
    public void givenIdAndProductWhenExistsThenReturnExistingProduct() throws Exception {
        var id = 1;

        var product = defaultProduct().build();
        when(this.useCase.findById(anyInt())).thenReturn(product);

        this.mockMvc.perform(get(PRODUCT_ROUTE + "/{id}", id)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk());
    }

}