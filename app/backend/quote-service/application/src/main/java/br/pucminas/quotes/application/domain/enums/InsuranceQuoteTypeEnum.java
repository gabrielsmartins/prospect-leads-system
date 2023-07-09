package br.pucminas.quotes.application.domain.enums;

import br.pucminas.quotes.application.domain.exceptions.NoSuchQuoteTypeException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum InsuranceQuoteTypeEnum {

    LIFE("L"),
    PET("P"),
    ENTERPRISE("E"),
    TRAVEL("T");

    private final String code;

    public static InsuranceQuoteTypeEnum fromCode(String code) {
        return Stream.of(InsuranceQuoteTypeEnum.values())
                .filter(type -> type.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new NoSuchQuoteTypeException("Tipo de cotação não identificada"));
    }

}
