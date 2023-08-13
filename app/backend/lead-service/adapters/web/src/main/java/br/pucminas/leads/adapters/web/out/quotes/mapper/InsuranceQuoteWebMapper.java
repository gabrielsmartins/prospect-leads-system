package br.pucminas.leads.adapters.web.out.quotes.mapper;

import br.pucminas.leads.adapters.web.out.quotes.client.dto.SearchInsuranceQuoteDto;
import br.pucminas.leads.adapters.web.out.quotes.client.dto.UpdateInsuranceQuoteDto;
import br.pucminas.leads.application.domain.InsuranceQuote;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InsuranceQuoteWebMapper {

    public static InsuranceQuote mapToDomain(SearchInsuranceQuoteDto quoteDto) {
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

    public static UpdateInsuranceQuoteDto mapToDto(InsuranceQuote insuranceQuote) {
        if (insuranceQuote == null) {
            return null;
        }
        return UpdateInsuranceQuoteDto.builder()
                .withId(insuranceQuote.getId())
                .withProductId(insuranceQuote.getProductId())
                .withType(insuranceQuote.getType())
                .withCustomer(CustomerWebMapper.mapToDto(insuranceQuote.getCustomer()))
                .withTotalMonthlyPremiumAmount(insuranceQuote.getTotalMonthlyPremiumAmount())
                .withTotalCoverageAmount(insuranceQuote.getTotalCoverageAmount())
                .withAssistances(insuranceQuote.getAssistances())
                .withCoverages(insuranceQuote.getCoverages())
                .withCreatedAt(insuranceQuote.getCreatedAt())
                .withUpdatedAt(insuranceQuote.getUpdatedAt())
                .withFinished(insuranceQuote.isFinished())
                .withFinishedAt(insuranceQuote.getFinishedAt())
                .build();
    }

}
