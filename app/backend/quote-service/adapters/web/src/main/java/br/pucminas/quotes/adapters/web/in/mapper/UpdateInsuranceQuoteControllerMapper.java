package br.pucminas.quotes.adapters.web.in.mapper;

import br.pucminas.quotes.adapters.web.in.dto.UpdateInsuranceQuoteDto;
import br.pucminas.quotes.adapters.web.in.dto.enums.InsuranceQuoteTypeEnumDto;
import br.pucminas.quotes.application.domain.InsuranceQuote;
import br.pucminas.quotes.application.domain.enums.InsuranceQuoteTypeEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateInsuranceQuoteControllerMapper {

    public static UpdateInsuranceQuoteDto mapToDto(InsuranceQuote insuranceQuote) {
        if (insuranceQuote == null) {
            return null;
        }
        return UpdateInsuranceQuoteDto.builder()
                                      .withId(insuranceQuote.getId())
                                      .withType(InsuranceQuoteTypeEnumDto.fromCode(insuranceQuote.getType().getCode()))
                                      .withCustomer(CustomerControllerMapper.mapToDto(insuranceQuote.getCustomer()))
                                      .withProductId(insuranceQuote.getProductId())
                                      .withCreatedAt(insuranceQuote.getCreatedAt())
                                      .withFinished(insuranceQuote.isFinished())
                                      .withUpdatedAt(insuranceQuote.getUpdatedAt())
                                      .withFinishedAt(insuranceQuote.getFinishedAt())
                                      .build();
    }

    public static InsuranceQuote mapToDomain(UpdateInsuranceQuoteDto insuranceQuoteDto) {
        if (insuranceQuoteDto == null) {
            return null;
        }
        return InsuranceQuote.builder()
                             .withId(insuranceQuoteDto.getId())
                             .withType(InsuranceQuoteTypeEnum.fromCode(insuranceQuoteDto.getType().getCode()))
                             .withCustomer(CustomerControllerMapper.mapToDomain(insuranceQuoteDto.getCustomer()))
                             .withProductId(insuranceQuoteDto.getProductId())
                             .withFinished(insuranceQuoteDto.isFinished())
                             .build();
    }

}
