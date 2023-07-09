package br.pucminas.quotes.adapters.web.in.dto.enums;

import br.pucminas.quotes.application.domain.exceptions.NoSuchCustomerTypeException;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum GenderEnumDto {

    MALE("M", "MASCULINO"),
    FEMALE("F", "FEMININO"),
    UNKNOWN("D", "DESCONHECIDO");

    @JsonValue
    private final String code;
    private final String description;

    public static GenderEnumDto fromCode(String code) {
        return Stream.of(GenderEnumDto.values())
                .filter(type -> type.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new NoSuchCustomerTypeException("Gênero não identificado"));
    }

}
