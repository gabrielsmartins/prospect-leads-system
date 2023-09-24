package br.pucminas.leads.adapters.web.in.leads.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
public class LeadDto {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("insurance_quote")
    private InsuranceQuoteDto insuranceQuote;

    @JsonProperty("finished")
    private boolean finished;

    @JsonProperty("finished_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime finishedAt;

    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime processedAt;

}
