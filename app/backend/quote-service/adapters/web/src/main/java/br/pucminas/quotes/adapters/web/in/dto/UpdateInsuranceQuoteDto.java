package br.pucminas.quotes.adapters.web.in.dto;

import br.pucminas.quotes.adapters.web.in.dto.enums.InsuranceQuoteTypeEnumDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
public class UpdateInsuranceQuoteDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @JsonProperty(value = "type")
    @NotNull
    private InsuranceQuoteTypeEnumDto type;

    @JsonProperty(value = "customer")
    @NotNull
    private CustomerDto customer;

    @JsonProperty(value = "product_id")
    @NotNull
    private Integer productId;

    @JsonProperty(value = "finished")
    private boolean finished;

    @JsonProperty(value = "created_at", access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime createdAt;

    @JsonProperty(value = "updated_at", access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime updatedAt;

    @JsonProperty(value = "finished_at", access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime finishedAt;

}
