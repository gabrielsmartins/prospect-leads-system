package br.pucminas.bff.application.ports.out.leads;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SendLeadPort {

    Mono<Void> send(UUID insuranceQuoteId);

}
