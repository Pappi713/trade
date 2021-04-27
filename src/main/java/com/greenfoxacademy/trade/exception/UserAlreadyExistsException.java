package com.greenfoxacademy.trade.exception;

public class UserAlreadyExistsException extends Exception {
  public UserAlreadyExistsException(String message) {
    super(message);
  }
}
