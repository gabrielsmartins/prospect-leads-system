package br.pucminas.products.application.domain.enums;

import br.pucminas.products.application.domain.exceptions.NoSuchCategoryException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum CategoryEnum {

    LIFE("L"),
    PET("P"),
    ENTERPRISE("E"),
    TRAVEL("T");

    private final String code;

    public static CategoryEnum fromCode(String code) {
        return Stream.of(CategoryEnum.values())
                     .filter(c -> c.getCode().equals(code))
                     .findFirst()
                     .orElseThrow(() -> new NoSuchCategoryException("Categoria não identificada"));
    }

}
