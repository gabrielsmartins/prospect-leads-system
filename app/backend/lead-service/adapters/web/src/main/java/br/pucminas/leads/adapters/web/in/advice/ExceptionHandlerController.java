package br.pucminas.leads.adapters.web.in.advice;

import br.pucminas.leads.adapters.web.in.advice.dto.ErrorDto;
import br.pucminas.leads.application.domain.exceptions.LeadNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(LeadNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleLeadNotFoundException(LeadNotFoundException ex){
        log.warn("Invalid Request", ex);
        return ErrorDto.builder()
                .withCode(404)
                .withMessage(ex.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.warn("Invalid Request", ex);
        var errorDTO = ErrorDto.builder()
                .withCode(400)
                .withMessage("Bad Request")
                .build();
        ex.getFieldErrors().forEach(fieldError -> {
            var errorFieldDTO = ErrorDto.ErrorFieldDto.builder()
                    .withName(fieldError.getField())
                    .withValue(fieldError.getRejectedValue())
                    .withMessage(fieldError.getDefaultMessage())
                    .build();
            errorDTO.addField(errorFieldDTO);
        });
        return errorDTO;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(Exception ex) {
        log.error("Error processing request", ex);
        return ErrorDto.builder()
                .withCode(500)
                .withMessage("Internal Server Error")
                .build();
    }

}
