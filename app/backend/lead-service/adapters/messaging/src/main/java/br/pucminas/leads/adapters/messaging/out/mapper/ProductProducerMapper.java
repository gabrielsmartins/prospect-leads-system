package br.pucminas.leads.adapters.messaging.out.mapper;

import br.pucminas.notifications.schemas.lead_processed.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductProducerMapper {

    public static Product mapToMessage(br.pucminas.leads.application.domain.Product product) {
        if (product == null) {
            return null;
        }
        return Product.newBuilder()
                .set
                .build();
    }
}
