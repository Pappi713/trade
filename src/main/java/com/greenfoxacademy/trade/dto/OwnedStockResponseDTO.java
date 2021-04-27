package com.greenfoxacademy.trade.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class OwnedStockResponseDTO {

  private Long ID;
  private Integer amount;
  private String type;
  private Double buyInPrice;

  public OwnedStockResponseDTO(Long ID, Integer amount, String type, Double buyInPrice) {
    this.ID = ID;
    this.amount = amount;
    this.type = type;
    this.buyInPrice = buyInPrice;
  }
}
