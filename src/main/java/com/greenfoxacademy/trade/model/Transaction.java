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
