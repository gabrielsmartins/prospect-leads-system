package br.pucminas.leads.adapters.streams.in.support;

import br.pucminas.leads.adapters.streams.in.dto.LeadDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LeadDtoSupport {

    public static LeadDto.LeadDtoBuilder defaultLeadDto() {
        return LeadDto.builder()
                      .withInsuranceQuoteId(UUID.randomUUID())
                      .withCreatedAt(LocalDateTime.now())
                      .withDeliveryTime(System.currentTimeMillis() + 3000L);
    }
}
