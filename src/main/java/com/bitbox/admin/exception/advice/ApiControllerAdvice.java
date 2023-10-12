package com.bitbox.admin.exception.advice;

import com.bitbox.admin.exception.DuplicationEmailException;
import com.bitbox.admin.exception.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {
    @ExceptionHandler(DuplicationEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDuplicationEmailException(DuplicationEmailException e) {
        return getErrorResponse(e);
    }

    private ErrorResponse getErrorResponse(RuntimeException e) {
        return ErrorResponse.builder()
                .message(e.getMessage())
                .build();
    }
}
