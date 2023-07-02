package br.pucminas.products.adapters.web.in.products.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum CategoryEnumDto {

    LIFE("L", "LIFE"),
    PET("P", "PET"),
    ENTERPRISE("E", "ENTERPRISE"),
    TRAVEL("T", "TRAVEL");

    private final String code;
    @JsonValue
    private final String description;

    public static CategoryEnumDto fromCode(String code) {
        return Stream.of(CategoryEnumDto.values())
                     .filter(c -> c.getCode().equals(code))
                     .findFirst()
                     .orElse(null);
    }

    public static CategoryEnumDto fromDescription(String description) {
        return Stream.of(CategoryEnumDto.values())
                    .filter(c -> c.getDescription().equalsIgnoreCase(description))
                    .findFirst()
                    .orElse(null);
    }

}
