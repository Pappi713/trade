package com.greenfoxacademy.trade.service;

import com.greenfoxacademy.trade.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

  private StockRepository stockRepository;

  @Autowired
  public StockService(StockRepository stockRepository){
    this.stockRepository = stockRepository;
  }
}
