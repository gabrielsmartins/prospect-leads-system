package br.pucminas.notifications.adapters.messaging.in.support;

import br.pucminas.leads.schemas.lead_processed.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LeadProcessedMessageSupport {

    public static LeadProcessed.Builder defaultLeadProcessedMessage() {
        return LeadProcessed.newBuilder()
                .setId(UUID.randomUUID())
                .setInsuranceQuote(defaultInsuranceQuoteMessage().build())
                .setCreatedAt(LocalDateTime.now())
                .setProcessedAt(LocalDateTime.now());
    }

    public static InsuranceQuote.Builder defaultInsuranceQuoteMessage() {
        return InsuranceQuote.newBuilder()
                             .setId(UUID.randomUUID())
                             .setType(InsuranceQuoteType.LIFE)
                             .setFinished(true)
                             .setCreatedAt(LocalDateTime.now())
                             .setUpdatedAt(LocalDateTime.now())
                             .setFinishedAt(LocalDateTime.now())
                             .setAssistances(List.of("Foo"))
                             .setCoverages(Map.of("Foo", BigDecimal.valueOf(1500)))
                             .setTotalCoverageAmount(BigDecimal.valueOf(1500))
                             .setTotalMonthlyPremiumAmount(BigDecimal.valueOf(2000))
                             .setProduct(defaultProductMessage().build())
                             .setCustomer(defaultCustomerMessage().build());
    }

    public static Product.Builder defaultProductMessage() {
        return Product.newBuilder()
                      .setId(1)
                      .setName("Life")
                      .setCategory(Category.LIFE)
                      .setActive(false)
                      .setAssistances(List.of("Foo"))
                      .setCoverages(Map.of("Foo", BigDecimal.valueOf(1500)))
                      .setTotalCoverageAmount(BigDecimal.valueOf(1500))
                      .setMaxTotalMonthlyPremiumAmount(BigDecimal.valueOf(5000))
                      .setMinTotalMonthlyPremiumAmount(BigDecimal.valueOf(1500))
                      .setSuggestedTotalMonthlyPremiumAmount(BigDecimal.valueOf(1700))
                      .setCreatedAt(LocalDateTime.now())
                      .setUpdatedAt(LocalDateTime.now());
    }

    public static Customer.Builder defaultCustomerMessage() {
        return Customer.newBuilder()
                .setName("John Wick")
                .setType(CustomerType.F)
                .setDocumentNumber("123456789")
                .setDateOfBirth(LocalDate.now().minusYears(30))
                .setEmail("johnwick@gmail.com")
                .setGender(CustomerGender.M)
                .setPhoneNumber(222222);
    }

}
