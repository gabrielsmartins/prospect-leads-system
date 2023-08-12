package br.pucminas.leads.adapters.persistence.config;

import br.pucminas.leads.adapters.persistence.entity.LeadEntity;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class LeadEntityEventListener extends AbstractMongoEventListener<LeadEntity> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<LeadEntity> event) {
        super.onBeforeConvert(event);
        var entity = event.getSource();
        if (entity.getInsuranceQuoteId() == null) {
            throw new IllegalArgumentException("Lead id must not be null");
        }
    }
}
