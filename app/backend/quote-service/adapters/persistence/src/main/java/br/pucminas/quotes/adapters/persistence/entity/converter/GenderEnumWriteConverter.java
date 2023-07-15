package br.pucminas.quotes.adapters.persistence.entity.converter;

import br.pucminas.quotes.application.domain.enums.GenderEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class GenderEnumReadConverter implements Converter<String, GenderEnum> {

    @Override
    public GenderEnum convert(String source) {
        if (source == null)
            return null;
        return GenderEnum.fromCode(source);
    }

}
