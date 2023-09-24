package br.pucminas.leads.adapters.web.in.leads.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@Getter
@Setter
@ToString
public class LeadDataDto {

    @JsonProperty("data")
    private List<LeadDto> leads;

}
