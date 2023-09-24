package br.pucminas.leads.adapters.web.in.leads.mapper;

import br.pucminas.leads.adapters.web.in.leads.dto.InsuranceQuoteDto;
import br.pucminas.leads.application.domain.InsuranceQuote;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InsuranceQuoteControllerMapper {

    public static InsuranceQuoteDto mapToDto(InsuranceQuote insuranceQuote) {
        if (insuranceQuote == null) {
            return null;
        }
        return InsuranceQuoteDto.builder()
                .withId(insuranceQuote.getId())
                .withType(insuranceQuote.getType())
                .withProductId(insuranceQuote.getProductId())
                .withProduct(ProductControllerMapper.mapToDto(insuranceQuote.getProduct()))
                .withCoverages(insuranceQuote.getCoverages())
                .withAssistances(insuranceQuote.getAssistances())
                .withTotalMonthlyPremiumAmount(insuranceQuote.getTotalMonthlyPremiumAmount())
                .withTotalCoverageAmount(insuranceQuote.getTotalCoverageAmount())
                .withCreatedAt(insuranceQuote.getCreatedAt())
                .withUpdatedAt(insuranceQuote.getUpdatedAt())
                .withFinishedAt(insuranceQuote.getFinishedAt())
                .withFinished(insuranceQuote.isFinished())
                .withCustomer(CustomerControllerMapper.mapToDto(insuranceQuote.getCustomer()))
                .build();
    }

}
