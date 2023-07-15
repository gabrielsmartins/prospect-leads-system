package br.pucminas.quotes.adapters.persistence.entity.converter;

import br.pucminas.quotes.application.domain.enums.CustomerTypeEnum;
import org.springframework.core.convert.converter.Converter;


public class CustomerTypeEnumConverter implements Converter<String, CustomerTypeEnum> {
    @Override
    public CustomerTypeEnum convert(String source) {
        return null;
    }
}
