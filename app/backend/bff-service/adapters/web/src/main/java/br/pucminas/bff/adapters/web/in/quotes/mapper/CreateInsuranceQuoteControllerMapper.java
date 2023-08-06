package br.pucminas.bff.adapters.web.in.quotes.mapper;


import br.pucminas.bff.adapters.web.in.quotes.dto.CreateInsuranceQuoteDto;
import br.pucminas.bff.application.domain.InsuranceQuote;
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
                                     .withType(insuranceQuote.getType())
                                     .withCustomer(CustomerControllerMapper.mapToDto(insuranceQuote.getCustomer()))
                                     .withProductId(insuranceQuote.getProductId())
                                     .withCreatedAt(insuranceQuote.getCreatedAt())
                                     .build();
    }

    public static InsuranceQuote mapToDomain(CreateInsuranceQuoteDto insuranceQuoteDto) {
        if (insuranceQuoteDto == null) {
            return null;
        }
        return InsuranceQuote.builder()
                             .withId(insuranceQuoteDto.getId())
                             .withType(insuranceQuoteDto.getType())
                             .withCustomer(CustomerControllerMapper.mapToDomain(insuranceQuoteDto.getCustomer()))
                             .withProductId(insuranceQuoteDto.getProductId())
                             .build();
    }

}
