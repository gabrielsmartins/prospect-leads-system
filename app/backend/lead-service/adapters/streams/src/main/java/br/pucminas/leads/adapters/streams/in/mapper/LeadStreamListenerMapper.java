package br.pucminas.leads.adapters.streams.in.mapper;

import br.pucminas.leads.adapters.streams.in.dto.LeadDto;
import br.pucminas.leads.application.domain.Lead;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LeadStreamListenerMapper {

    public static Lead mapToDomain(LeadDto leadDto) {
        if (leadDto == null) {
            return null;
        }
        return Lead.builder()
                   .withInsuranceQuoteId(leadDto.getInsuranceQuoteId())
                   .withCreatedAt(leadDto.getCreatedAt())
                   .withDeliveryTime(leadDto.getDeliveryTime())
                   .build();
    }

}
