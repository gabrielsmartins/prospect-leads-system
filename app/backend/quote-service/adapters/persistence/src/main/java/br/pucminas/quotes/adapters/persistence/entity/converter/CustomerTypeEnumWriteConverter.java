package br.pucminas.quotes.adapters.persistence.entity.converter;

import br.pucminas.quotes.application.domain.enums.CustomerTypeEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;


@WritingConverter
public class CustomerTypeEnumReadConverter implements Converter<String, CustomerTypeEnum> {

    @Override
    public CustomerTypeEnum convert(String source) {
        if (source == null)
            return null;
        return CustomerTypeEnum.fromCode(source);
    }

}
