package com.greenfoxacademy.trade.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @Column(unique = true)
  private String username;
  @JsonIgnore
  private String password;

  private Integer balance;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<Stock> portfolio;


}
