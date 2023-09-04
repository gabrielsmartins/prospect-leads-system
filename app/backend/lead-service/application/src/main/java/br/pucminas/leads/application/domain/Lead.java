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

    private InsuranceQuote insuranceQuote;
    private Product product;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
    private boolean sent;

}
