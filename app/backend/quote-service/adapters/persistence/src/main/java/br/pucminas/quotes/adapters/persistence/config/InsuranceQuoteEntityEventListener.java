package br.pucminas.quotes.adapters.persistence.config;

import br.pucminas.quotes.adapters.persistence.entity.InsuranceQuoteEntity;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import java.util.UUID;

public class InsuranceQuoteEntityEventListener extends AbstractMongoEventListener<InsuranceQuoteEntity> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<InsuranceQuoteEntity> event) {
        super.onBeforeConvert(event);
        var entity = event.getSource();

        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
    }
}
