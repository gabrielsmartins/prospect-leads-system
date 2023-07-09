package br.pucminas.quotes.application.domain.exceptions;

public class NoSuchQuoteTypeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoSuchQuoteTypeException(String message) {
        super(message);
    }

}
