package br.pucminas.products.adapters.persistence.entity.converter;


import br.pucminas.products.application.domain.enums.CategoryEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CategoryEnumConverter implements AttributeConverter<CategoryEnum, String> {

    @Override
    public String convertToDatabaseColumn(CategoryEnum attribute) {
        if (attribute == null)
            return null;
        return attribute.getCode();
    }

    @Override
    public CategoryEnum convertToEntityAttribute(String code) {
        if (code ==null)
            return null;
        return CategoryEnum.fromCode(code);
    }

}
