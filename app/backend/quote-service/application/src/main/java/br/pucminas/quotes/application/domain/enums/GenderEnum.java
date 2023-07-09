package br.pucminas.quotes.application.domain.enums;

import br.pucminas.quotes.application.domain.exceptions.NoSuchCustomerTypeException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum GenderEnum {

    MALE("M", "MASCULINO"),
    FEMALE("F", "FEMININO"),
    UNKNOWN("D", "DESCONHECIDO");

    private final String code;
    private final String description;

    public static GenderEnum fromCode(String code) {
        return Stream.of(GenderEnum.values())
                .filter(type -> type.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new NoSuchCustomerTypeException("Gênero não identificado"));
    }

}
