package br.pucminas.leads.adapters.messaging.out.mapper;

import br.pucminas.notifications.schemas.lead_processed.InsuranceQuote;
import br.pucminas.notifications.schemas.lead_processed.InsuranceQuoteType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InsuranceQuoteProducerMapper {

    public static InsuranceQuote mapToMessage(br.pucminas.leads.application.domain.InsuranceQuote insuranceQuote) {
        if (insuranceQuote == null) {
            return null;
        }
        var customerMessage = CustomerMapper.mapToMessage(insuranceQuote.getCustomer());
        var productMessage = ProductProducerMapper.mapToMessage(insuranceQuote.getProduct());
        return InsuranceQuote.newBuilder()
                             .setId(insuranceQuote.getId())
                             .setType(InsuranceQuoteType.valueOf(insuranceQuote.getType()))
                             .setTotalMonthlyPremiumAmount(insuranceQuote.getTotalMonthlyPremiumAmount())
                             .setTotalCoverageAmount(insuranceQuote.getTotalCoverageAmount())
                             .setCoverages(insuranceQuote.getCoverages())
                             .setAssistances(insuranceQuote.getAssistances())
                             .setFinished(insuranceQuote.isFinished())
                             .setCreatedAt(insuranceQuote.getCreatedAt())
                             .setUpdatedAt(insuranceQuote.getUpdatedAt())
                             .setFinishedAt(insuranceQuote.getFinishedAt())
                             .setCustomer(customerMessage)
                             .setProduct(productMessage)
                             .build();
    }

}
