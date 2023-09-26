package br.pucminas.leads.adapters.email.mapper;

import br.pucminas.leads.adapters.email.dto.EmailDto;
import br.pucminas.leads.application.domain.Notification;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailAdapterMapper {

    public static String FROM_EMAIL = "tcc-pucminas-2023@hotmail.com";

    public static EmailDto mapToEmail(Notification notification) {
        if (notification == null) {
            return null;
        }
        var insuranceQuote = notification.getInsuranceQuote();
        var customer = insuranceQuote.getCustomer();
        var product = insuranceQuote.getProduct();

        var emailDto = new EmailDto();
        emailDto.setFromEmail(FROM_EMAIL);
        emailDto.setToEmail(customer.getEmail());
        emailDto.setCustomerName(customer.getName());
        emailDto.setProductName(product.getName());
        emailDto.setTotalCoverageAmount(insuranceQuote.getTotalCoverageAmount());
        emailDto.setTotalMonthlyPremiumAmount(insuranceQuote.getTotalMonthlyPremiumAmount());
        emailDto.setCoverages(insuranceQuote.getCoverages());
        emailDto.setAssistances(insuranceQuote.getAssistances());
        return emailDto;
    }

}
