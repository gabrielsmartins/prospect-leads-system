package br.pucminas.leads.application.ports.out;

import br.pucminas.leads.application.domain.InsuranceQuote;

import java.util.UUID;

public interface UpdateInsuranceQuotePort {

    void update(UUID id, InsuranceQuote insuranceQuote);

}
