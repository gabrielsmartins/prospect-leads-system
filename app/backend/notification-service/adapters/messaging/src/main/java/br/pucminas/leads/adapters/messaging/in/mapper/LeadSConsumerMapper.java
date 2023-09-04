package br.pucminas.leads.adapters.messaging.in.mapper;

import br.pucminas.leads.adapters.messaging.in.dto.LeadDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LeadSConsumerMapper {

    public static Lead mapToDomain(LeadDto leadDto) {
        if (leadDto == null) {
            return null;
        }
        return Lead.builder()
                   .withInsuranceQuoteId(leadDto.getInsuranceQuoteId())
                   .withCreatedAt(leadDto.getCreatedAt())
                   .build();
    }

}
