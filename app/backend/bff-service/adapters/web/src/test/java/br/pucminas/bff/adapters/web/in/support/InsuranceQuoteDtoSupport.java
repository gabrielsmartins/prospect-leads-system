package br.pucminas.bff.adapters.web.in.support;


import br.pucminas.bff.adapters.web.in.quotes.dto.CreateInsuranceQuoteDto;
import br.pucminas.bff.adapters.web.in.quotes.dto.UpdateInsuranceQuoteDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

import static br.pucminas.bff.adapters.web.in.support.CustomerDtoSupport.defaultCustomerDto;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InsuranceQuoteDtoSupport {

    public static CreateInsuranceQuoteDto.CreateInsuranceQuoteDtoBuilder defaultCreateInsuranceQuoteDto() {
        return CreateInsuranceQuoteDto.builder()
                                      .withId(UUID.randomUUID())
                                      .withType("L")
                                      .withCustomer(defaultCustomerDto().build())
                                      .withProductId(1)
                                      .withCreatedAt(LocalDateTime.now());
    }

    public static UpdateInsuranceQuoteDto.UpdateInsuranceQuoteDtoBuilder defaultUpdateInsuranceQuoteDto() {
        return UpdateInsuranceQuoteDto.builder()
                                      .withId(UUID.randomUUID())
                                      .withType("L")
                                      .withCustomer(defaultCustomerDto().build())
                                      .withProductId(1)
                                      .withFinished(false)
                                      .withCreatedAt(LocalDateTime.now())
                                      .withUpdatedAt(LocalDateTime.now())
                                      .withFinishedAt(LocalDateTime.now());
    }

}
