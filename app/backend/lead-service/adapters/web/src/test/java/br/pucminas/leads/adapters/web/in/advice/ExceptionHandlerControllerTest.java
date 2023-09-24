package br.pucminas.leads.adapters.web.in.advice;

import br.pucminas.leads.adapters.web.config.web.ObjectMapperConfiguration;
import br.pucminas.leads.adapters.web.in.leads.LeadController;
import br.pucminas.leads.application.domain.exceptions.LeadNotFoundException;
import br.pucminas.leads.application.ports.in.SearchLeadUseCase;
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

import java.util.UUID;

import static br.pucminas.leads.adapters.web.config.ControllerRoutes.LEAD_ROUTE;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {LeadController.class})
@Import(ObjectMapperConfiguration.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class ExceptionHandlerControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    private SearchLeadUseCase useCase;


    @Test
    @DisplayName("Given Product When Error Then Return Internal Server Error")
    public void givenProductWhenErrorThenReturnInternalServerError() throws Exception {
        var id = UUID.randomUUID();

        when(this.useCase.findById(id)).thenThrow(new RuntimeException("Error"));

        this.mockMvc.perform(get(LEAD_ROUTE + "/{id}", id)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Given Id When Not Exists Then Return Return Not Found")
    public void givenIdWhenNotExistsThenReturnNotFound() throws Exception {
        var id = UUID.randomUUID();

        when(this.useCase.findById(id)).thenThrow(new LeadNotFoundException("Lead not found"));

        this.mockMvc.perform(get(LEAD_ROUTE + "/{id}", id)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

}