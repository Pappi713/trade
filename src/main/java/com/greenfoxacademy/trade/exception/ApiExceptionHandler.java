package com.greenfoxacademy.trade.exception;

import com.greenfoxacademy.trade.dto.ErrorDto;
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

  @ExceptionHandler (value = InsufficientFundsException.class)
  public ResponseEntity hanleInsufficientFundsException(InsufficientFundsException e) {
    return ResponseEntity.status(422).body(new ErrorDto(e.getMessage()));
  }

  @ExceptionHandler (value = PositionAlreadyClosedException.class)
  public ResponseEntity handlePositionAlreadyClosedException(PositionAlreadyClosedException e) {
    return ResponseEntity.status(422).body(new ErrorDto(e.getMessage()));
  }

  @ExceptionHandler (value = RuntimeException.class)
  public ResponseEntity handleRuntimeExceptions(RuntimeException e) {
    return ResponseEntity.status(422).body(new ErrorDto(e.getMessage()));
  }


}
