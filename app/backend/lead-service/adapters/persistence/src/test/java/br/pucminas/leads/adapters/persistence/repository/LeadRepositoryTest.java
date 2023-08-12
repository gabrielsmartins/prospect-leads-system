package br.pucminas.leads.adapters.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import static br.pucminas.leads.adapters.persistence.support.LeadEntitySupport.defaultLeadEntity;
import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ActiveProfiles("test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class LeadRepositoryTest {

    private final LeadRepository repository;

    @Test
    @DisplayName("Given Lead When Save Then Return Saved Lead")
    public void givenLeadWhenSaveThenReturnSavedLead() {
        var leadEntity = defaultLeadEntity().build();

        var savedLeadEntity = this.repository.save(leadEntity);

        assertThat(savedLeadEntity).isNotNull();
    }

    @Test
    @DisplayName("Given Id When Exists Then Return Lead")
    public void givenIdWhenExistsThenReturnLead() {
        var leadEntity = defaultLeadEntity().build();
        var savedLeadEntity = this.repository.save(leadEntity);

        var optionalLeadEntity = this.repository.findById(savedLeadEntity.getInsuranceQuoteId());

        assertThat(optionalLeadEntity).isPresent();
    }
}