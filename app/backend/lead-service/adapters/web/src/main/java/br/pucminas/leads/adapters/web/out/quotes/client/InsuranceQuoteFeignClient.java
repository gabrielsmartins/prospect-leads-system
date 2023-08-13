package br.pucminas.leads.adapters.web.out.quotes.client;

import br.pucminas.leads.adapters.web.out.quotes.client.dto.SearchInsuranceQuoteDto;
import br.pucminas.leads.adapters.web.out.quotes.client.dto.UpdateInsuranceQuoteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

@FeignClient(name = "quotes", url = "${webservice.quotes.url}")
public interface InsuranceQuoteFeignClient {

    @GetMapping("/{id}")
    ResponseEntity<SearchInsuranceQuoteDto> findById(@PathVariable("id") UUID id);

    @PutMapping("/{id}")
    ResponseEntity<UpdateInsuranceQuoteDto> update(@PathVariable("id") UUID id, UpdateInsuranceQuoteDto insuranceQuoteDto);

}
