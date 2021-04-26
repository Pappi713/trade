package com.greenfoxacademy.trade.repository;

import com.greenfoxacademy.trade.model.HistoricData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricDataRepository extends CrudRepository<HistoricData,Long> {
}
