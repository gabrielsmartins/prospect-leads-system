package br.pucminas.leads.adapters.web.out.products.client.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum CategoryEnumDto {

    LIFE("LIFE"),
    PET("PET"),
    ENTERPRISE("ENTERPRISE"),
    TRAVEL("TRAVEL");

    @JsonValue
    private final String description;

    public static CategoryEnumDto fromDescription(String description) {
        return Stream.of(CategoryEnumDto.values())
                    .filter(c -> c.getDescription().equalsIgnoreCase(description))
                    .findFirst()
                    .orElse(null);
    }

}
