package com.example.onlineshop.handler;

import com.example.onlineshop.dto.ErrorDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Log4j2
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleUnexpectedException(EntityNotFoundException e) {
        log.error("Entity was not found. Message: {}, class: {}. method: {}", "Entity was not found.", e.getStackTrace()[1].getClassName(), e.getStackTrace()[1].getMethodName());
        return createErrorDto(e.getStackTrace(), "Entity was not found.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ErrorDto> handleNullException(NullPointerException e) {
        log.error("Null pointer. Message: {}, class: {}", e.getMessage(), e.getStackTrace()[0].getClassName());
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        return createErrorDto(e.getStackTrace(), e.getMessage(), notFound);

    }


    private ResponseEntity<ErrorDto> createErrorDto(StackTraceElement[] stackTraceElements, String message, HttpStatus statusCode) {
        String className = stackTraceElements[0].getClassName();
        String methodName = stackTraceElements[0].getMethodName();
        ErrorDto errorDTO = new ErrorDto(" ", message, statusCode.value(), "error", "development", methodName, className);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(errorDTO);
    }

}
