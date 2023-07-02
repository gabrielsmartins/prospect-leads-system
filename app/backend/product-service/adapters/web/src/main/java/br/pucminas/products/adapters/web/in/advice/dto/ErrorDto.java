package br.pucminas.products.adapters.web.in.advice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorDto {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("fields")
    private final List<ErrorFieldDto> fields = new LinkedList<>();

    public Integer addField(ErrorFieldDto field){
        this.fields.add(field);
        return this.fields.size();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    @Builder(setterPrefix = "with")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class ErrorFieldDto {

        @JsonProperty("name")
        private String name;

        @JsonProperty("value")
        private Object value;

        @JsonProperty("message")
        private String message;

    }
}
