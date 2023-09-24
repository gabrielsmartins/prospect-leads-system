package br.pucminas.bff.adapters.web.in.advice;

import br.pucminas.bff.adapters.web.in.advice.dto.ErrorDto;
import br.pucminas.bff.adapters.web.in.advice.dto.ErrorDto.ErrorFieldDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

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

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<ErrorDto> handleWebClientResponseException(WebClientResponseException ex) {
        log.error(append("exception", ex), "Error processing request");
        var statusCode = ex.getStatusCode();
        var errorDto =  ErrorDto.builder()
                                .withCode(statusCode.value())
                                .withMessage(ex.getMessage())
                                .build();
        return new ResponseEntity<>(errorDto, statusCode);
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
