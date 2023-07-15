package br.pucminas.quotes.adapters.web.in.support;


import br.pucminas.quotes.adapters.web.in.dto.CreateInsuranceQuoteDto;
import br.pucminas.quotes.adapters.web.in.dto.UpdateInsuranceQuoteDto;
import br.pucminas.quotes.adapters.web.in.dto.enums.InsuranceQuoteTypeEnumDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InsuranceQuoteDtoSupport {

    public static CreateInsuranceQuoteDto.CreateInsuranceQuoteDtoBuilder defaultCreateInsuranceQuoteDto() {
        return CreateInsuranceQuoteDto.builder()
                                      .withId(UUID.randomUUID())
                                      .withType(InsuranceQuoteTypeEnumDto.LIFE)
                                      .withCustomer(CustomerDtoSupport.defaultCustomerDto().build())
                                      .withProductId(1)
                                      .withCreatedAt(LocalDateTime.now());
    }

    public static UpdateInsuranceQuoteDto.UpdateInsuranceQuoteDtoBuilder defaultUpdateInsuranceQuoteDto() {
        return UpdateInsuranceQuoteDto.builder()
                                      .withId(UUID.randomUUID())
                                      .withType(InsuranceQuoteTypeEnumDto.LIFE)
                                      .withCustomer(CustomerDtoSupport.defaultCustomerDto().build())
                                      .withProductId(1)
                                      .withFinished(false)
                                      .withCreatedAt(LocalDateTime.now())
                                      .withUpdatedAt(LocalDateTime.now())
                                      .withFinishedAt(LocalDateTime.now());
    }

}
