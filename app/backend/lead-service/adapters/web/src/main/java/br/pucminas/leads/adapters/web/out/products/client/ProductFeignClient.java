package br.pucminas.leads.adapters.web.out.products.client;


import br.pucminas.leads.adapters.web.out.products.client.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products", url = "${webservice.products.url}")
public interface ProductFeignClient {

    @GetMapping("/{id}")
    ResponseEntity<ProductDto> findById(@PathVariable("id") Integer id);

}
