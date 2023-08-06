package br.pucminas.bff.adapters.web.in.quotes.mapper;


import br.pucminas.bff.adapters.web.in.quotes.dto.SearchInsuranceQuoteDto;
import br.pucminas.bff.application.domain.InsuranceQuote;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchInsuranceQuoteControllerMapper {

    public static SearchInsuranceQuoteDto mapToDto(InsuranceQuote insuranceQuote) {
        if (insuranceQuote == null) {
            return null;
        }
        return SearchInsuranceQuoteDto.builder()
                                      .withId(insuranceQuote.getId())
                                      .withType(insuranceQuote.getType())
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
