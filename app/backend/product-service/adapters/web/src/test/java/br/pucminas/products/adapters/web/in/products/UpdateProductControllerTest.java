package br.pucminas.products.adapters.web.in.products;

import br.pucminas.products.adapters.web.config.ObjectMapperConfiguration;
import br.pucminas.products.application.domain.Product;
import br.pucminas.products.application.ports.in.UpdateProductUseCase;
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
import static br.pucminas.products.adapters.web.support.ProductDtoSupport.defaultUpdateProductDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UpdateProductController.class)
@Import(ObjectMapperConfiguration.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateProductControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @MockBean
    private UpdateProductUseCase useCase;

    @Test
    @DisplayName("Given Id And Product When Exists Then Return Updated Product")
    public void givenIdAndProductWhenExistsThenReturnUpdatedProduct() throws Exception {
        var id = 1;
        var productDto = defaultUpdateProductDto().build();
        var content = this.objectMapper.writeValueAsString(productDto);

        when(this.useCase.update(anyInt(), any(Product.class))).thenAnswer(invocation -> invocation.getArgument(1));

        this.mockMvc.perform(patch(PRODUCT_ROUTE + "/{id}", id)
                    .content(content)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk());
    }

}