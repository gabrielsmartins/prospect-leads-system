package br.pucminas.bff.adapters.web.in.quotes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Email;
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
    private String type;

    @JsonProperty(value = "gender")
    @NotNull
    private String gender;

    @JsonProperty(value = "date_of_birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate dateOfBirth;

    @JsonProperty("email")
    @Email
    @NotNull
    private String email;

    @JsonProperty("phone_number")
    @NotNull
    private Integer phoneNumber;

}
