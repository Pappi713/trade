package com.greenfoxacademy.trade.service;

import com.greenfoxacademy.trade.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  private TransactionRepository transactionRepository;

  @Autowired
  public TransactionService(TransactionRepository transactionRepository){
    this.transactionRepository = transactionRepository;
  }
}
