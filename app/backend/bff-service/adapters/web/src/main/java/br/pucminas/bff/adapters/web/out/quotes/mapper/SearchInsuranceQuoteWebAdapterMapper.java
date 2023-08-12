package br.pucminas.bff.adapters.web.out.quotes.mapper;

import br.pucminas.bff.adapters.web.out.quotes.dto.SearchInsuranceQuoteDto;
import br.pucminas.bff.application.domain.InsuranceQuote;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchInsuranceQuoteWebAdapterMapper {

    public static InsuranceQuote mapToDomain(SearchInsuranceQuoteDto quoteDto) {
        if (quoteDto == null) {
            return null;
        }
        return InsuranceQuote.builder()
                             .withId(quoteDto.getId())
                             .withProductId(quoteDto.getProductId())
                             .withType(quoteDto.getType())
                             .withCustomer(CustomerWebAdapterMapper.mapToDomain(quoteDto.getCustomer()))
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
