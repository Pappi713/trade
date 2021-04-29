package com.greenfoxacademy.trade.dto;

import com.greenfoxacademy.trade.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
  private String type;
  private Integer amount;
  private Double price;
  private String order;
  private Double profit;

  public TransactionDto(Transaction transaction) {
    this.type = transaction.getType();
    this.amount = transaction.getAmount();
    this.price = transaction.getStockPrice();
    this.order = transaction.getTransactionType();
    this.profit = transaction.getProfit();
  }
}

/*
{
    “balance”: 1200,
    “trade”:
    {
    “type”: “GOOGL”,
    “amount”: 1,
    “price”: 1100,
    “order”: “buy”
    “profit”: 0
    }

    }
*/

