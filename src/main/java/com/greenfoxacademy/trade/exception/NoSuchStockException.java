package com.greenfoxacademy.trade.exception;

public class NoSuchStockException extends Exception{
  public NoSuchStockException() {
    super("no owned stock with the given id");
  }
}
