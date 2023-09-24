package br.pucminas.leads.adapters.web.in.leads.mapper;

import br.pucminas.leads.adapters.web.in.leads.dto.LeadDto;
import br.pucminas.leads.application.domain.Lead;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LeadControllerMapper {

    public static LeadDto mapToDto(Lead lead) {
        if (lead == null) {
            return null;
        }
        return LeadDto.builder()
                      .withId(lead.getId())
                      .withCreatedAt(lead.getCreatedAt())
                      .withFinished(lead.isFinished())
                      .withFinishedAt(lead.getFinishedAt())
                      .withProcessedAt(lead.getProcessedAt())
                      .withInsuranceQuote(InsuranceQuoteControllerMapper.mapToDto(lead.getInsuranceQuote()))
                      .build();
    }
}
