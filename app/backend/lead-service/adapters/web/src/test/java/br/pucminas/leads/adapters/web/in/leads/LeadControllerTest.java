package br.pucminas.leads.adapters.web.in.leads;

import br.pucminas.leads.adapters.web.config.web.ObjectMapperConfiguration;
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

import java.util.List;
import java.util.UUID;

import static br.pucminas.leads.adapters.web.config.ControllerRoutes.LEAD_ROUTE;
import static br.pucminas.leads.application.support.LeadSupport.defaultLead;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LeadController.class)
@Import(ObjectMapperConfiguration.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class LeadControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    private SearchLeadUseCase useCase;

    @Test
    @DisplayName("Given Leads When Exists Then Return Leads")
    public void givenLeadsWhenExistsThenReturnLeads() throws Exception {
        var product = defaultLead().build();
        when(this.useCase.findAll()).thenReturn(List.of(product));

        this.mockMvc.perform(get(LEAD_ROUTE)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Given Id When Exists Then Return Existing Lead")
    public void givenIdWhenExistsThenReturnExistingLead() throws Exception {
        var product = defaultLead().build();
        when(this.useCase.findById(any(UUID.class))).thenReturn(product);

        var id = UUID.randomUUID();
        this.mockMvc.perform(get(LEAD_ROUTE + "/{id}", id)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk());
    }

}