package com.greenfoxacademy.trade.repository;

import com.greenfoxacademy.trade.model.OwnedStock;
import org.springframework.data.repository.CrudRepository;



public interface OwnedStockRepository extends CrudRepository<OwnedStock, Long> {
}
