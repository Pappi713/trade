package com.greenfoxacademy.trade.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDto {
  private String order;
  private String type;
  private Integer amount;
  private Double price;
  private Integer ID;
}
