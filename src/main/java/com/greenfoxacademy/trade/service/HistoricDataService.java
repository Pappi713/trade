package com.greenfoxacademy.trade.service;

import com.greenfoxacademy.trade.model.HistoricData;
import com.greenfoxacademy.trade.repository.HistoricDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricDataService {

  private HistoricDataRepository historicDataRepository;

  @Autowired
  public HistoricDataService(HistoricDataRepository historicDataRepository){
    this.historicDataRepository=historicDataRepository;
  }

}
