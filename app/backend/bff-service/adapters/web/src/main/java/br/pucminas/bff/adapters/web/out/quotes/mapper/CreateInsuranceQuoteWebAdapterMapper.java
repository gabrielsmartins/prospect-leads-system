package br.pucminas.bff.adapters.web.out.quotes.mapper;

import br.pucminas.bff.adapters.web.out.quotes.dto.CreateInsuranceQuoteDto;
import br.pucminas.bff.application.domain.InsuranceQuote;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateInsuranceQuoteWebAdapterMapper {

    public static CreateInsuranceQuoteDto mapToDto(InsuranceQuote quote) {
        if (quote == null) {
            return null;
        }
        return CreateInsuranceQuoteDto.builder()
                                      .withProductId(quote.getProductId())
                                      .withType(quote.getType())
                                      .withCustomer(CustomerWebAdapterMapper.mapToDto(quote.getCustomer()))
                                      .build();
    }

    public static InsuranceQuote mapToDomain(CreateInsuranceQuoteDto quoteDto) {
        if (quoteDto == null) {
            return null;
        }
        return InsuranceQuote.builder()
                             .withId(quoteDto.getId())
                             .withProductId(quoteDto.getProductId())
                             .withType(quoteDto.getType())
                             .withCreatedAt(quoteDto.getCreatedAt())
                             .withCustomer(CustomerWebAdapterMapper.mapToDomain(quoteDto.getCustomer()))
                             .build();
    }
}
