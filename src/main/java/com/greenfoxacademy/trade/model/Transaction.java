package com.greenfoxacademy.trade.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String type;

  public Transaction(String type, String date, Double stockPrice, Double transactionValue, Integer amount,
                     String transactionType, User user) {
    this.type = type;
    this.date = date;
    this.stockPrice = stockPrice;
    this.transactionValue = transactionValue;
    this.amount = amount;
    this.transactionType = transactionType;
    this.user = user;
  }

  public Transaction(String type, String date, Double stockPrice, Double transactionValue, Integer amount,
                     String transactionType, Double profit, User user) {
    this.type = type;
    this.date = date;
    this.stockPrice = stockPrice;
    this.transactionValue = transactionValue;
    this.amount = amount;
    this.transactionType = transactionType;
    this.profit = profit;
    this.user = user;
  }

  private String date;

  @Column(name = "stock_price")
  private Double stockPrice;

  @Column(name = "transaction_value")
  private Double transactionValue;


  private Integer amount;

  @Column(name = "transaction_type")
  private String transactionType;

  private Double profit=0.0;

  @ManyToOne
  @JoinColumn(name = "user_name", nullable = false)
  private User user;
}
