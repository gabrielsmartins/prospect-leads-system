package br.pucminas.quotes.adapters.web.in.mapper;

import br.pucminas.quotes.adapters.web.in.dto.CreateInsuranceQuoteDto;
import br.pucminas.quotes.adapters.web.in.dto.enums.InsuranceQuoteTypeEnumDto;
import br.pucminas.quotes.application.domain.InsuranceQuote;
import br.pucminas.quotes.application.domain.enums.InsuranceQuoteTypeEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateInsuranceQuoteControllerMapper {

    public static CreateInsuranceQuoteDto mapToDto(InsuranceQuote insuranceQuote) {
        if (insuranceQuote == null) {
            return null;
        }
        return CreateInsuranceQuoteDto.builder()
                                     .withId(insuranceQuote.getId())
                                     .withType(InsuranceQuoteTypeEnumDto.fromCode(insuranceQuote.getType().getCode()))
                                     .withCustomer(CustomerControllerMapper.mapToDto(insuranceQuote.getCustomer()))
                                     .withProductId(insuranceQuote.getProductId())
                                     .withTotalYearlyPremiumAmount(insuranceQuote.getTotalYearlyPremiumAmount())
                                     .withTotalMonthlyPremiumAmount(insuranceQuote.getTotalMonthlyPremiumAmount())
                                     .withTotalCoverageAmount(insuranceQuote.getTotalCoverageAmount())
                                     .withCreatedAt(insuranceQuote.getCreatedAt())
                                     .withCoverages(insuranceQuote.getCoverages())
                                     .withAssistances(insuranceQuote.getAssistances())
                                     .build();
    }

    public static InsuranceQuote mapToDomain(CreateInsuranceQuoteDto insuranceQuoteDto) {
        if (insuranceQuoteDto == null) {
            return null;
        }
        var quote =  InsuranceQuote.builder()
                                   .withId(insuranceQuoteDto.getId())
                                   .withType(InsuranceQuoteTypeEnum.fromCode(insuranceQuoteDto.getType().getCode()))
                                   .withCustomer(CustomerControllerMapper.mapToDomain(insuranceQuoteDto.getCustomer()))
                                   .withProductId(insuranceQuoteDto.getProductId())
                                   .withTotalYearlyPremiumAmount(insuranceQuoteDto.getTotalYearlyPremiumAmount())
                                   .withTotalMonthlyPremiumAmount(insuranceQuoteDto.getTotalMonthlyPremiumAmount())
                                   .build();
        insuranceQuoteDto.getCoverages().forEach(quote::addCoverage);
        insuranceQuoteDto.getAssistances().forEach(quote::addAssistance);
        return quote;
    }

}
