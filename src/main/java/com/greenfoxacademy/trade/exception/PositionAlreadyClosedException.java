package com.greenfoxacademy.trade.exception;

public class PositionAlreadyClosedException extends Exception {
  public PositionAlreadyClosedException() {
    super("Not enough stocks");
  }
}
