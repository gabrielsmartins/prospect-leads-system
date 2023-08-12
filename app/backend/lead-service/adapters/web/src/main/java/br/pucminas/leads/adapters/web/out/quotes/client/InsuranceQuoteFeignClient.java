package br.pucminas.leads.adapters.web.out.quotes.client;

import br.pucminas.leads.adapters.web.out.quotes.client.dto.InsuranceQuoteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "quotes")
public interface InsuranceQuoteFeignClient {

    @GetMapping("/{id}")
    ResponseEntity<InsuranceQuoteDto> findById(@PathVariable("id") UUID id);

}
