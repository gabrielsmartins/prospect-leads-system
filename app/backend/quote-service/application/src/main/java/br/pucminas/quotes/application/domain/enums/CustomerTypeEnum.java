package br.pucminas.quotes.application.domain.enums;

import br.pucminas.quotes.application.domain.exceptions.NoSuchCustomerTypeException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum CustomerTypeEnum {

    NATURAL("F", "FÍSICA"),
    LEGAL("J", "JURÍDICA");

    private final String code;
    private final String description;

    public static CustomerTypeEnum fromCode(String code) {
        return Stream.of(CustomerTypeEnum.values())
                .filter(type -> type.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new NoSuchCustomerTypeException("Tipo de cliente não identificado"));
    }


}
