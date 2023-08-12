package br.pucminas.leads.adapters.persistence.config;

import br.pucminas.leads.adapters.persistence.entity.LeadEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class MongoDbConfiguration {

    @Bean
    public MongoCustomConversions customConversions(){
        List<Converter<?, ?>> converterList = new ArrayList<Converter<?, ?>>();
        return new MongoCustomConversions(converterList);
    }

    @Bean
    public LeadEntityEventListener eventListener() {
        return new LeadEntityEventListener();
    }

    @Bean
    public BeforeConvertCallback<LeadEntity> beforeSaveCallback() {
        return (entity, collection) -> {
            if (entity.getInsuranceQuoteId() == null) {
                entity.setInsuranceQuoteId(UUID.randomUUID());
            }
            return entity;
        };
    }

}
