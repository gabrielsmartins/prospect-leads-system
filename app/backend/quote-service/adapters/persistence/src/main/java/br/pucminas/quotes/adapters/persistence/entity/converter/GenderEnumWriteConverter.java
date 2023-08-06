package br.pucminas.quotes.adapters.persistence.entity.converter;

import br.pucminas.quotes.application.domain.enums.GenderEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class GenderEnumWriteConverter implements Converter<GenderEnum, String> {

    @Override
    public String convert(GenderEnum source) {
        if (source == null)
            return null;
        return source.getCode();
    }

}
