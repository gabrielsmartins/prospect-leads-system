package br.pucminas.leads.adapters.web.support;


import br.pucminas.leads.adapters.web.out.quotes.client.dto.SearchInsuranceQuoteDto;
import br.pucminas.leads.adapters.web.out.quotes.client.dto.UpdateInsuranceQuoteDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InsuranceQuoteDtoSupport {

    public static SearchInsuranceQuoteDto.SearchInsuranceQuoteDtoBuilder defaultSearchInsuranceQuoteDto() {
        return SearchInsuranceQuoteDto.builder()
                                      .withId(UUID.randomUUID())
                                      .withType("L")
                                      .withCustomer(CustomerDtoSupport.defaultCustomerDto().build())
                                      .withProductId(1)
                                      .withFinished(false)
                                      .withTotalCoverageAmount(BigDecimal.ONE)
                                      .withTotalMonthlyPremiumAmount(BigDecimal.TEN)
                                      .withAssistances(List.of("Foo"))
                                      .withCoverages(Map.of("Foo", BigDecimal.TEN))
                                      .withFinishedAt(LocalDateTime.now())
                                      .withCreatedAt(LocalDateTime.now())
                                      .withUpdatedAt(LocalDateTime.now())
                                      .withFinishedAt(LocalDateTime.now());
    }

    public static UpdateInsuranceQuoteDto.UpdateInsuranceQuoteDtoBuilder defaultUpdateInsuranceQuoteDto() {
        return UpdateInsuranceQuoteDto.builder()
                                    .withId(UUID.randomUUID())
                                    .withType("L")
                                    .withCustomer(CustomerDtoSupport.defaultCustomerDto().build())
                                    .withProductId(1)
                                    .withFinished(false)
                                    .withTotalCoverageAmount(BigDecimal.ONE)
                                    .withTotalMonthlyPremiumAmount(BigDecimal.TEN)
                                    .withAssistances(List.of("Foo"))
                                    .withCoverages(Map.of("Foo", BigDecimal.TEN))
                                    .withFinishedAt(LocalDateTime.now())
                                    .withCreatedAt(LocalDateTime.now())
                                    .withUpdatedAt(LocalDateTime.now())
                                    .withFinishedAt(LocalDateTime.now());
    }

}
