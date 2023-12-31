package br.pucminas.quotes.adapters.web.in.support;


import br.pucminas.quotes.adapters.web.in.dto.CreateInsuranceQuoteDto;
import br.pucminas.quotes.adapters.web.in.dto.UpdateInsuranceQuoteDto;
import br.pucminas.quotes.adapters.web.in.dto.enums.InsuranceQuoteTypeEnumDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

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
                                      .withTotalCoverageAmount(BigDecimal.valueOf(50))
                                      .withTotalMonthlyPremiumAmount(BigDecimal.TEN)
                                      .withCoverages(new LinkedHashMap<>(Map.of("Acidente", BigDecimal.TEN)))
                                      .withAssistances(new LinkedList<>(List.of("Manutenção")))
                                      .withCreatedAt(LocalDateTime.now())
                                      .withUpdatedAt(LocalDateTime.now())
                                      .withFinishedAt(LocalDateTime.now());
    }

}
