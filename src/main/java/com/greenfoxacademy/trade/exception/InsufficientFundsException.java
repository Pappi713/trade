package com.greenfoxacademy.trade.exception;

public class InsufficientFundsException extends Exception {
  public InsufficientFundsException() {
    super("Insufficient Funds");
  }
}
