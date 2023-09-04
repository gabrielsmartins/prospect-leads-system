package br.pucminas.leads.adapters.streams.in.mapper;

import br.pucminas.leads.adapters.streams.in.dto.LeadDto;
import br.pucminas.leads.application.domain.InsuranceQuote;
import br.pucminas.leads.application.domain.Lead;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LeadStreamListenerMapper {

    public static Lead mapToDomain(LeadDto leadDto) {
        if (leadDto == null) {
            return null;
        }
        var insuranceQuoteId = leadDto.getInsuranceQuoteId();
        var insuranceQuote = new InsuranceQuote();
        insuranceQuote.setId(insuranceQuoteId);
        return Lead.builder()
                   .withInsuranceQuote(insuranceQuote)
                   .withCreatedAt(leadDto.getCreatedAt())
                   .build();
    }

}
