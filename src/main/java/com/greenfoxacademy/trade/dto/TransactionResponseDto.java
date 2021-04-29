package com.greenfoxacademy.trade.dto;

import com.greenfoxacademy.trade.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {
  private Double balance;
  private TransactionDto trade;
  public TransactionResponseDto(Double balance, Transaction transaction) {
    this.balance = balance;
    this.trade = new TransactionDto(transaction);
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

