package br.pucminas.bff.adapters.web.out.support;


import br.pucminas.bff.adapters.web.out.quotes.dto.CreateInsuranceQuoteDto;
import br.pucminas.bff.adapters.web.out.quotes.dto.SearchInsuranceQuoteDto;
import br.pucminas.bff.adapters.web.out.quotes.dto.UpdateInsuranceQuoteDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InsuranceQuoteDtoSupport {

    public static CreateInsuranceQuoteDto.CreateInsuranceQuoteDtoBuilder defaultCreateInsuranceQuoteDto() {
        return CreateInsuranceQuoteDto.builder()
                                      .withId(UUID.randomUUID())
                                      .withType("L")
                                      .withCustomer(CustomerDtoSupport.defaultCustomerDto().build())
                                      .withProductId(1)
                                      .withCreatedAt(LocalDateTime.now());
    }

    public static UpdateInsuranceQuoteDto.UpdateInsuranceQuoteDtoBuilder defaultUpdateInsuranceQuoteDto() {
        return UpdateInsuranceQuoteDto.builder()
                                      .withId(UUID.randomUUID())
                                      .withType("L")
                                      .withCustomer(CustomerDtoSupport.defaultCustomerDto().build())
                                      .withProductId(1)
                                      .withFinished(false)
                                      .withCreatedAt(LocalDateTime.now())
                                      .withUpdatedAt(LocalDateTime.now())
                                      .withFinishedAt(LocalDateTime.now());
    }

    public static SearchInsuranceQuoteDto.SearchInsuranceQuoteDtoBuilder defaultSearchInsuranceQuoteDto() {
        return SearchInsuranceQuoteDto.builder()
                .withId(UUID.randomUUID())
                .withType("L")
                .withCustomer(CustomerDtoSupport.defaultCustomerDto().build())
                .withProductId(1)
                .withFinished(false)
                .withTotalCoverageAmount(BigDecimal.ONE)
                .withTotalYearlyPremiumAmount(BigDecimal.valueOf(120))
                .withTotalMonthlyPremiumAmount(BigDecimal.TEN)
                .withAssistances(List.of("Foo"))
                .withCoverages(Map.of("Foo", BigDecimal.TEN))
                .withFinishedAt(LocalDateTime.now())
                .withCreatedAt(LocalDateTime.now())
                .withUpdatedAt(LocalDateTime.now())
                .withFinishedAt(LocalDateTime.now());
    }

}
