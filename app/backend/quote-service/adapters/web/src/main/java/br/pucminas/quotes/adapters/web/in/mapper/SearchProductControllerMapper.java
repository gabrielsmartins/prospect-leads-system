package br.pucminas.quotes.adapters.web.in.mapper;

import br.pucminas.quotes.adapters.web.in.dto.SearchInsuranceQuoteDto;
import br.pucminas.quotes.adapters.web.in.dto.enums.InsuranceQuoteTypeEnumDto;
import br.pucminas.quotes.application.domain.InsuranceQuote;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchProductControllerMapper {

    public static SearchInsuranceQuoteDto mapToDto(InsuranceQuote insuranceQuote) {
        if (insuranceQuote == null) {
            return null;
        }
        return SearchInsuranceQuoteDto.builder()
                                      .withId(insuranceQuote.getId())
                                      .withType(InsuranceQuoteTypeEnumDto.fromCode(insuranceQuote.getType().getCode()))
                                      .withCustomer(CustomerControllerMapper.mapToDto(insuranceQuote.getCustomer()))
                                      .withProductId(insuranceQuote.getProductId())
                                      .withTotalYearlyPremiumAmount(insuranceQuote.getTotalYearlyPremiumAmount())
                                      .withTotalMonthlyPremiumAmount(insuranceQuote.getTotalMonthlyPremiumAmount())
                                      .withTotalCoverageAmount(insuranceQuote.getTotalCoverageAmount())
                                      .withCreatedAt(insuranceQuote.getCreatedAt())
                                      .withFinished(insuranceQuote.isFinished())
                                      .withUpdatedAt(insuranceQuote.getUpdatedAt())
                                      .withFinishedAt(insuranceQuote.getFinishedAt())
                                      .withCoverages(insuranceQuote.getCoverages())
                                      .withAssistances(insuranceQuote.getAssistances())
                                      .build();
    }

}
