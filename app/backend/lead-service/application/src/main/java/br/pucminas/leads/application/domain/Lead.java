package br.pucminas.leads.application.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
public class Lead {

    private UUID id;
    private InsuranceQuote insuranceQuote;
    private boolean finished;
    private LocalDateTime finishedAt;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;

}
