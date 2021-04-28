package com.greenfoxacademy.trade.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PortfolioResponseDTO {

  private Double balance;
  @JsonProperty("stocks")
  private List<OwnedStockResponseDTO> ownedStockResponseDtoList;

  public PortfolioResponseDTO(Double balance,
                              List<OwnedStockResponseDTO> ownedStockResponseDtoList) {
    this.balance = balance;
    this.ownedStockResponseDtoList = ownedStockResponseDtoList;
  }
}
