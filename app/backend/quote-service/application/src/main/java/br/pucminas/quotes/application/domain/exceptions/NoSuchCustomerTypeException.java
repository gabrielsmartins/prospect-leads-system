package br.pucminas.quotes.application.domain.exceptions;

public class NoSuchCustomerTypeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoSuchCustomerTypeException(String message) {
        super(message);
    }

}
