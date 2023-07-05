package br.pucminas.bff.adapters.web.out.products.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateProductDto {

    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "name")
    @NotNull
    private String name;

    @JsonProperty(value = "active")
    private Boolean active;

    @JsonProperty(value = "category")
    @NotNull
    private CategoryEnumDto category;

    @JsonProperty(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime createdAt;


}
