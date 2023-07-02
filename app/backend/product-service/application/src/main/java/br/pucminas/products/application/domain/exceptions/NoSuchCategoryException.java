package br.pucminas.products.application.domain.exceptions;

public class NoSuchCategoryException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoSuchCategoryException(String message) {
        super(message);
    }

}
