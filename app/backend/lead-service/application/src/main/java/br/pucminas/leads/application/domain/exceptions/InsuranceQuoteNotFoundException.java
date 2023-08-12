package br.pucminas.leads.application.domain.exceptions;

public class InsuranceQuoteNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InsuranceQuoteNotFoundException(String message) {
        super(message);
    }

}
