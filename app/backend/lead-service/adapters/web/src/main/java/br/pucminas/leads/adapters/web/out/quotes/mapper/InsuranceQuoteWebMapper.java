package br.pucminas.leads.adapters.web.out.quotes.mapper;

import br.pucminas.leads.adapters.web.out.quotes.client.dto.InsuranceQuoteDto;
import br.pucminas.leads.application.domain.InsuranceQuote;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InsuranceQuoteWebMapper {

    public static InsuranceQuote mapToDomain(InsuranceQuoteDto quoteDto) {
        if (quoteDto == null) {
            return null;
        }
        return InsuranceQuote.builder()
                .withId(quoteDto.getId())
                .withProductId(quoteDto.getProductId())
                .withType(quoteDto.getType())
                .withCustomer(CustomerWebMapper.mapToDomain(quoteDto.getCustomer()))
                .withTotalMonthlyPremiumAmount(quoteDto.getTotalMonthlyPremiumAmount())
                .withTotalCoverageAmount(quoteDto.getTotalCoverageAmount())
                .withAssistances(quoteDto.getAssistances())
                .withCoverages(quoteDto.getCoverages())
                .withCreatedAt(quoteDto.getCreatedAt())
                .withUpdatedAt(quoteDto.getUpdatedAt())
                .withFinishedAt(quoteDto.getFinishedAt())
                .withFinished(quoteDto.isFinished())
                .build();
    }

}
