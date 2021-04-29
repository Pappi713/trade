package com.greenfoxacademy.trade.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HistoryResponseDTO {

  private String type;
  private String date;
  private Double stockPrice;
  private Integer amount;
  private Double transactionValue;
  private String transactionType;
  private Double profit;

  public HistoryResponseDTO(String type, String date, Double stockPrice, Integer amount,
                            Double transactionValue, String transactionType,Double profit ) {
    this.type = type;
    this.date = date;
    this.stockPrice = stockPrice;
    this.amount = amount;
    this.transactionValue = transactionValue;
    this.transactionType = transactionType;
    this.profit=profit;
  }
}
