package br.pucminas.leads.adapters.email.mapper;

import br.pucminas.leads.application.domain.Customer;
import br.pucminas.leads.application.domain.InsuranceQuote;
import br.pucminas.leads.application.domain.Notification;
import br.pucminas.leads.application.domain.Product;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.SimpleMailMessage;

import java.util.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailAdapterMapper {

    public static SendTemplatedEmailRequest mapToMessage(Notification notification) {
        var insuranceQuote = notification.getInsuranceQuote();
        var customer = insuranceQuote.getCustomer();
        var product = insuranceQuote.getProduct();

        var simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailDetails.getFromEmail());
        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setSubject(emailDetails.getSubject());
        simpleMailMessage.setText(emailDetails.getBody());

        var destination = new Destination();
        List<String> toAddresses = new ArrayList<>();
        String[] emails = simpleMailMessage.getTo();
        Collections.addAll(toAddresses, Objects.requireNonNull(emails));
        destination.setToAddresses(toAddresses);

        Map<String, Object> params = new HashMap<>();
        params.put("customerName", customer.getName());
        params.put("productName", product.getName());

        var templateData = toJson(params);

        var templatedEmailRequest = new SendTemplatedEmailRequest();
        templatedEmailRequest.withDestination(destination);
        templatedEmailRequest.withTemplate("InsuranceTemplate");
        templatedEmailRequest.withTemplateData(templateData);
        templatedEmailRequest.withSource(simpleMailMessage.getFrom());
        return templatedEmailRequest;
    }

    @SneakyThrows
    private static String toJson(Map<String, Object> params) {
        var objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(params);
    }

}
