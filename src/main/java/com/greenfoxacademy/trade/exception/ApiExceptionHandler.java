package com.greenfoxacademy.trade.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler (value = UserAlreadyExistsException.class)
  public ResponseEntity handleUserAlreadyExistsException() {
    return ResponseEntity.status(422).build();
  }

  @ExceptionHandler (value = UserNotFoundException.class)
  public ResponseEntity handleUserNotFoundException() {
    return ResponseEntity.status(422).build();
  }


}
