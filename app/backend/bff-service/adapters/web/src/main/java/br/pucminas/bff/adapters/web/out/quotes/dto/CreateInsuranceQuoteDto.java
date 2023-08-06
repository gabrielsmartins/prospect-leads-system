package br.pucminas.bff.adapters.web.out.quotes.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
public class CreateInsuranceQuoteDto {

    @JsonProperty(value = "id")
    private UUID id;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "customer")
    private CustomerDto customer;

    @JsonProperty(value = "product_id")
    private Integer productId;

    @JsonProperty(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime createdAt;
}
