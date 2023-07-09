package br.pucminas.quotes.adapters.web.in.dto.enums;

import br.pucminas.quotes.application.domain.exceptions.NoSuchCustomerTypeException;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum CustomerTypeEnumDto {

    NATURAL("F", "FÍSICA"),
    LEGAL("J", "JURÍDICA");

    @JsonValue
    private final String code;
    private final String description;

    public static CustomerTypeEnumDto fromCode(String code) {
        return Stream.of(CustomerTypeEnumDto.values())
                .filter(type -> type.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new NoSuchCustomerTypeException("Tipo de cliente não identificado"));
    }


}
