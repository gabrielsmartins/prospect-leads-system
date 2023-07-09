package br.pucminas.quotes.adapters.web.in.dto.enums;

import br.pucminas.quotes.application.domain.exceptions.NoSuchQuoteTypeException;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum InsuranceQuoteTypeEnumDto {

    LIFE("L"),
    PET("P"),
    ENTERPRISE("E"),
    TRAVEL("T");

    @JsonValue
    private final String code;

    public static InsuranceQuoteTypeEnumDto fromCode(String code) {
        return Stream.of(InsuranceQuoteTypeEnumDto.values())
                .filter(type -> type.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new NoSuchQuoteTypeException("Tipo de cotação não identificada"));
    }

}
