package br.pucminas.bff.application.ports.out.leads;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CaptureLeadPort {

    Mono<Void> capture(UUID insuranceQuoteId);

}
