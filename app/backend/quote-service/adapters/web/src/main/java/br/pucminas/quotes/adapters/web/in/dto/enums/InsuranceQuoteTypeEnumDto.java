package br.pucminas.quotes.adapters.web.in.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum InsuranceQuoteTypeEnumDto {

    LIFE("L", "LIFE"),
    PET("P", "PET"),
    ENTERPRISE("E", "ENTERPRISE"),
    TRAVEL("T", "TRAVEL");

    private final String code;
    @JsonValue
    private final String description;

    public static InsuranceQuoteTypeEnumDto fromCode(String code) {
        return Stream.of(InsuranceQuoteTypeEnumDto.values())
                     .filter(typeEnumDto -> typeEnumDto.getCode().equals(code))
                     .findFirst()
                     .orElse(null);
    }

    public static InsuranceQuoteTypeEnumDto fromDescription(String description) {
        return Stream.of(InsuranceQuoteTypeEnumDto.values())
                     .filter(typeEnumDto -> typeEnumDto.getDescription().equalsIgnoreCase(description))
                     .findFirst()
                     .orElse(null);
    }

}
