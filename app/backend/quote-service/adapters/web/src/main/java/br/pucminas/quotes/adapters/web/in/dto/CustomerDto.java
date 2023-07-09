package br.pucminas.quotes.adapters.web.in.dto;

import br.pucminas.quotes.adapters.web.in.dto.enums.CustomerTypeEnumDto;
import br.pucminas.quotes.adapters.web.in.dto.enums.GenderEnumDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
public class CustomerDto {

    @JsonProperty(value = "document_number")
    @NotNull
    private String documentNumber;

    @JsonProperty(value = "name")
    @NotNull
    private String name;

    @JsonProperty(value = "type")
    @NotNull
    private CustomerTypeEnumDto type;

    @JsonProperty(value = "gender")
    @NotNull
    private GenderEnumDto gender;

    @JsonProperty(value = "date_of_birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate dateOfBirth;

}
