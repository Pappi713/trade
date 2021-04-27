package com.greenfoxacademy.trade.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponseDTO {
  private String token;
  private Double balance;


  public LoginResponseDTO(String token, Double balance) {
    this.token = token;
    this.balance = balance;
  }
}
