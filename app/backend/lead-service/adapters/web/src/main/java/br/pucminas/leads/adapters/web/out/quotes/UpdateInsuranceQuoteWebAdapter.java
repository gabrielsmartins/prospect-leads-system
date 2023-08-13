package br.pucminas.leads.adapters.web.out.quotes;

import br.pucminas.leads.adapters.web.out.quotes.client.InsuranceQuoteFeignClient;
import br.pucminas.leads.adapters.web.out.quotes.mapper.InsuranceQuoteWebMapper;
import br.pucminas.leads.application.domain.InsuranceQuote;
import br.pucminas.leads.application.ports.out.UpdateInsuranceQuotePort;
import br.pucminas.leads.common.stereotype.WebAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import static net.logstash.logback.argument.StructuredArguments.kv;
import static net.logstash.logback.marker.Markers.append;

@WebAdapter
@RequiredArgsConstructor
@Slf4j
public class UpdateInsuranceQuoteWebAdapter implements UpdateInsuranceQuotePort {

    private final InsuranceQuoteFeignClient client;

    @Override
    public void update(UUID id, InsuranceQuote insuranceQuote) {
        log.info(append("insurance_quote", insuranceQuote), "Mapping insurance quote");
        var insuranceQuoteDto = InsuranceQuoteWebMapper.mapToDto(insuranceQuote);

        log.info(append("insurance_quote", insuranceQuoteDto), "Updating insurance quote by id: {}", kv("id", id));
        var response = this.client.update(id, insuranceQuoteDto);
        log.info(append("response", response), "Insurance quote was updated successfully");
    }

}
