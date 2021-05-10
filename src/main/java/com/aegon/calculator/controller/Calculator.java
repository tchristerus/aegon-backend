package com.aegon.calculator.controller;

import com.aegon.calculator.dto.CalculationResult;
import com.aegon.calculator.enums.Operation;
import com.aegon.calculator.service.SimpleCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculation")
@RequiredArgsConstructor
public class Calculator {

    private final SimpleCalculator simpleCalculator;

    @GetMapping("/{a}/{b}/{operation}")
    public CalculationResult calculate(@PathVariable int a, @PathVariable int b, @PathVariable Operation operation) {
        return this.simpleCalculator.calculate(a, b, operation);
    }
}
