package br.pucminas.leads.adapters.web.in.leads.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
public class CustomerDto {

    @JsonProperty("document_number")
    private String documentNumber;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("date_of_birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone_number")
    private Integer phoneNumber;

}
