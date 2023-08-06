package br.pucminas.bff.adapters.web.in.quotes.mapper;


import br.pucminas.bff.adapters.web.in.quotes.dto.UpdateInsuranceQuoteDto;
import br.pucminas.bff.application.domain.InsuranceQuote;
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
                                      .withType(insuranceQuote.getType())
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
                             .withType(insuranceQuoteDto.getType())
                             .withCustomer(CustomerControllerMapper.mapToDomain(insuranceQuoteDto.getCustomer()))
                             .withProductId(insuranceQuoteDto.getProductId())
                             .withFinished(insuranceQuoteDto.isFinished())
                             .build();
    }

}
