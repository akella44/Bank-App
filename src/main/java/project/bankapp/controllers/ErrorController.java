package project.bankapp.controllers;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import project.bankapp.dto.requests.ErrorResponse;
import project.bankapp.exceptions.InvalidArgException;
import project.bankapp.exceptions.NotEnoughCashException;
import project.bankapp.exceptions.WrongCardNumberException;
import project.bankapp.exceptions.WrongCurrencyTypeException;

import javax.security.auth.login.CredentialException;

@ControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException e){
        return handleExceptionInternal(new ErrorResponse(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CredentialException.class)
    public ResponseEntity<Object> handleWrongCredentialException(CredentialException e){
        return handleExceptionInternal(new ErrorResponse(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(InvalidArgException.class)
    public ResponseEntity<Object> handleInvalidArgException(InvalidArgException ex){
        return handleExceptionInternal(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongCardNumberException.class)
    public ResponseEntity<Object> handleWrongCardNumberException(WrongCardNumberException ex){
        return handleExceptionInternal(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongCurrencyTypeException.class)
    public ResponseEntity<Object> handleWrongCardNumberException(WrongCurrencyTypeException ex){
        return handleExceptionInternal(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotEnoughCashException.class)
    public ResponseEntity<Object> handleWrongCardNumberException(NotEnoughCashException ex){
        return handleExceptionInternal(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
    private ResponseEntity<Object> handleExceptionInternal(ErrorResponse body, HttpStatus code){
        return ResponseEntity.status(code).contentType(MediaType.APPLICATION_JSON).body(body);
    }
}
