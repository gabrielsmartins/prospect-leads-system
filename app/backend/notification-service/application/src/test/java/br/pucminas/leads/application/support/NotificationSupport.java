package br.pucminas.leads.application.support;

import br.pucminas.leads.application.domain.Notification;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import static br.pucminas.leads.application.support.CustomerSupport.defaultCustomer;
import static br.pucminas.leads.application.support.InsuranceQuoteSupport.defaultInsuranceQuote;
import static br.pucminas.leads.application.support.ProductSupport.defaultProduct;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationSupport {

    public static Notification.NotificationBuilder defaultNotification() {
        return Notification.builder()
                .withCustomer(defaultCustomer().build())
                .withProduct(defaultProduct().build())
                .withInsuranceQuote(defaultInsuranceQuote().build());
    }

}
