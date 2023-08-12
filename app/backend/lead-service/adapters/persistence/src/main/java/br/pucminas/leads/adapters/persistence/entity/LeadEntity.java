package br.pucminas.leads.adapters.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
public class LeadEntity {

    @Id
    private UUID insuranceQuoteId;

    @Field("created_at")
    private LocalDateTime createdAt;

    @Field("processed_at")
    private LocalDateTime processedAt;

    @Field("sent")
    private boolean sent;

}
