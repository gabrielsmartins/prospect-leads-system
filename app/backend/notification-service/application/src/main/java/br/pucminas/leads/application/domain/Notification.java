package br.pucminas.leads.application.domain;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
public class Notification {

    private InsuranceQuote insuranceQuote;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;

}
