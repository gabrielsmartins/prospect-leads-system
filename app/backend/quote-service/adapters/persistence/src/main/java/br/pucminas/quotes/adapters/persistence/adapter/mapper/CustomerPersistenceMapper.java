package br.pucminas.quotes.adapters.persistence.adapter.mapper;

import br.pucminas.quotes.adapters.persistence.entity.CustomerEntity;
import br.pucminas.quotes.application.domain.Customer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerPersistenceMapper {

    public static CustomerEntity mapToEntity(Customer customer) {
        if (customer == null) {
            return null;
        }
        return CustomerEntity.builder()
                            .withGender(customer.getGender())
                            .withType(customer.getType())
                            .withDocumentNumber(customer.getDocumentNumber())
                            .withDateOfBirth(customer.getDateOfBirth())
                            .withName(customer.getName())
                            .build();
    }

    public static Customer mapToDomain(CustomerEntity customerEntity) {
        if (customerEntity == null) {
            return null;
        }
        return Customer.builder()
                        .withGender(customerEntity.getGender())
                        .withType(customerEntity.getType())
                        .withDocumentNumber(customerEntity.getDocumentNumber())
                        .withDateOfBirth(customerEntity.getDateOfBirth())
                        .withName(customerEntity.getName())
                        .build();
    }
}
