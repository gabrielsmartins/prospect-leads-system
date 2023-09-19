package br.pucminas.leads.application.domain.exceptions;

public class LeadNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LeadNotFoundException(String message) {
        super(message);
    }

}
