package br.pucminas.products.application.domain.exceptions;

public class UnsupportedSuggestedPremiumAmountException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UnsupportedSuggestedPremiumAmountException(String message) {
        super(message);
    }

}
