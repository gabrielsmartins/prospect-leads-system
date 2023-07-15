package br.pucminas.quotes.adapters.persistence.entity.converter;

import br.pucminas.quotes.application.domain.enums.CustomerTypeEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;


@ReadingConverter
public class CustomerTypeEnumWriteConverter implements Converter<CustomerTypeEnum, String> {

    @Override
    public String convert(CustomerTypeEnum source) {
        if (source == null)
            return null;
        return source.getCode();
    }

}
