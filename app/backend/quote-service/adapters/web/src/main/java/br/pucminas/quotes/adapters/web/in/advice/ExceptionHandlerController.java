package br.pucminas.quotes.adapters.web.in.advice;

import br.pucminas.quotes.adapters.web.in.advice.dto.ErrorDto;
import br.pucminas.quotes.adapters.web.in.advice.dto.ErrorDto.ErrorFieldDto;
import br.pucminas.quotes.application.domain.exceptions.InsuranceQuoteNotFoundException;
import br.pucminas.quotes.application.domain.exceptions.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import static net.logstash.logback.marker.Markers.append;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleMethodArgumentNotValidException(WebExchangeBindException ex) {
        log.warn(append("exception", ex), "Invalid Request");
        var errorDTO = ErrorDto.builder()
                .withCode(400)
                .withMessage(ex.getMessage())
                .build();
        ex.getFieldErrors().forEach(fieldError -> {
            var errorFieldDTO = ErrorFieldDto.builder()
                    .withName(fieldError.getField())
                    .withValue(fieldError.getRejectedValue())
                    .withMessage(fieldError.getDefaultMessage())
                    .build();
            errorDTO.addField(errorFieldDTO);
        });
        return errorDTO;
    }

    @ExceptionHandler(InsuranceQuoteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleInsuranceQuoteNotFoundException(InsuranceQuoteNotFoundException ex) {
        log.error(append("exception", ex), "Error processing request");
        return ErrorDto.builder()
                .withCode(500)
                .withMessage(ex.getMessage())
                .build();
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDto handleProductNotFoundException(ProductNotFoundException ex) {
        log.error(append("exception", ex), "Error processing request");
        return ErrorDto.builder()
                .withCode(422)
                .withMessage(ex.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(Exception ex) {
        log.error(append("exception", ex), "Error processing request");
        return ErrorDto.builder()
                        .withCode(500)
                        .withMessage(ex.getMessage())
                        .build();
    }

}
