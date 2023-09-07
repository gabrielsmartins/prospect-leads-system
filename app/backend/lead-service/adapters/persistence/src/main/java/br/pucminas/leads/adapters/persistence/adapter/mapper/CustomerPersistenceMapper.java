package br.pucminas.leads.adapters.persistence.adapter.mapper;

import br.pucminas.leads.adapters.persistence.entity.CustomerEntity;
import br.pucminas.leads.application.domain.Customer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerPersistenceMapper {

    public static CustomerEntity mapToEntity(Customer customer) {
        if (customer == null) {
            return null;
        }
        return CustomerEntity.builder()
                            .withDocumentNumber(customer.getDocumentNumber())
                            .withName(customer.getName())
                            .withType(customer.getType())
                            .withGender(customer.getGender())
                            .withDateOfBirth(customer.getDateOfBirth())
                            .withPhoneNumber(customer.getPhoneNumber())
                            .withEmail(customer.getEmail())
                            .build();
    }

    public static Customer mapToDomain(CustomerEntity customerEntity) {
        if (customerEntity == null) {
            return null;
        }
        return Customer.builder()
                        .withDocumentNumber(customerEntity.getDocumentNumber())
                        .withName(customerEntity.getName())
                        .withType(customerEntity.getType())
                        .withGender(customerEntity.getGender())
                        .withDateOfBirth(customerEntity.getDateOfBirth())
                        .withPhoneNumber(customerEntity.getPhoneNumber())
                        .withEmail(customerEntity.getEmail())
                        .build();
    }

}
