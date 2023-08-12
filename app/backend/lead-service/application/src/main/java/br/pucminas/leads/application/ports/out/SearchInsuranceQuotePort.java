package br.pucminas.leads.application.ports.out;

import br.pucminas.leads.application.domain.InsuranceQuote;

import java.util.Optional;
import java.util.UUID;

public interface SearchInsuranceQuotePort {

    Optional<InsuranceQuote> findById(UUID id);

}
