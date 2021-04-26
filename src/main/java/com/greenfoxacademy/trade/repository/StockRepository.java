package com.greenfoxacademy.trade.repository;

import com.greenfoxacademy.trade.model.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<Stock,Long> {
}
