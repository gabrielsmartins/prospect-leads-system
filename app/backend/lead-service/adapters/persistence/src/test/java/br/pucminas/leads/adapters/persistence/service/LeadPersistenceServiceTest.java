package br.pucminas.leads.adapters.persistence.service;


import br.pucminas.leads.adapters.persistence.entity.LeadEntity;
import br.pucminas.leads.adapters.persistence.repository.LeadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static br.pucminas.leads.adapters.persistence.support.LeadEntitySupport.defaultLeadEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LeadPersistenceServiceTest {

    private LeadPersistenceService service;
    private LeadRepository repository;

    @BeforeEach
    public void setup() {
        this.repository = mock(LeadRepository.class);
        this.service = new LeadPersistenceService(this.repository);
    }

    @Test
    @DisplayName("Given Lead When Save Then Return Saved Lead")
    public void givenLeadWhenSaveThenReturnSavedLead() {
        var leadEntity = defaultLeadEntity().build();

        when(this.repository.save(any(LeadEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var savedLeadEntity = this.service.save(leadEntity);

        assertThat(savedLeadEntity).isNotNull();
    }

    @Test
    @DisplayName("Given Id When Exists Then Return Lead")
    public void givenIdWhenExistsThenReturnLead() {
        var leadEntity = defaultLeadEntity().build();

        when(this.repository.findById(any(UUID.class))).thenReturn(Optional.of(leadEntity));

        var optionalLeadEntity = this.service.findById(leadEntity.getId());

        assertThat(optionalLeadEntity).isPresent();
    }

}