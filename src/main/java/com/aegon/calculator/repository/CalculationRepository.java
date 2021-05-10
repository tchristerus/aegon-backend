package com.aegon.calculator.repository;

import com.aegon.calculator.model.Calculation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationRepository extends CrudRepository<Calculation, Long> {
}
