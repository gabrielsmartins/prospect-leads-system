package br.pucminas.bff.adapters.web.out.quotes.mapper;

import br.pucminas.bff.adapters.web.out.quotes.dto.UpdateInsuranceQuoteDto;
import br.pucminas.bff.application.domain.InsuranceQuote;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateInsuranceQuoteWebAdapterMapper {

    public static UpdateInsuranceQuoteDto mapToDto(InsuranceQuote quote) {
        if (quote == null) {
            return null;
        }
        return UpdateInsuranceQuoteDto.builder()
                                      .withId(quote.getId())
                                      .withProductId(quote.getProductId())
                                      .withType(quote.getType())
                                      .withFinished(quote.isFinished())
                                      .withCustomer(CustomerWebAdapterMapper.mapToDto(quote.getCustomer()))
                                      .build();
    }

    public static InsuranceQuote mapToDomain(UpdateInsuranceQuoteDto quoteDto) {
        if (quoteDto == null) {
            return null;
        }
        return InsuranceQuote.builder()
                             .withId(quoteDto.getId())
                             .withProductId(quoteDto.getProductId())
                             .withType(quoteDto.getType())
                             .withCreatedAt(quoteDto.getCreatedAt())
                             .withUpdatedAt(quoteDto.getUpdatedAt())
                             .withFinishedAt(quoteDto.getFinishedAt())
                             .withFinished(quoteDto.isFinished())
                             .withCustomer(CustomerWebAdapterMapper.mapToDomain(quoteDto.getCustomer()))
                             .build();
    }
}
