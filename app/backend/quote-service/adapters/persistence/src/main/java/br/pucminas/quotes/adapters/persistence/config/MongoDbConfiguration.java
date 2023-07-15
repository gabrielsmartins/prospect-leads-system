package br.pucminas.quotes.adapters.persistence.config;

import br.pucminas.quotes.adapters.persistence.entity.InsuranceQuoteEntity;
import br.pucminas.quotes.adapters.persistence.entity.converter.CustomerTypeEnumReadConverter;
import br.pucminas.quotes.adapters.persistence.entity.converter.CustomerTypeEnumWriteConverter;
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
        converterList.add(new CustomerTypeEnumReadConverter());
        converterList.add(new CustomerTypeEnumWriteConverter());
        return new MongoCustomConversions(converterList);
    }

    @Bean
    public InsuranceQuoteEntityEventListener eventListener() {
        return new InsuranceQuoteEntityEventListener();
    }

    @Bean
    public BeforeConvertCallback<InsuranceQuoteEntity> beforeSaveCallback() {
        return (entity, collection) -> {
            if (entity.getId() == null) {
                entity.setId(UUID.randomUUID());
            }
            return entity;
        };
    }

}
