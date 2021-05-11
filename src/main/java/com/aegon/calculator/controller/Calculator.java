package com.aegon.calculator.controller;

import com.aegon.calculator.dto.CalculationDto;
import com.aegon.calculator.dto.CalculationResultDto;
import com.aegon.calculator.enums.Operation;
import com.aegon.calculator.service.SimpleCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculation")
@RequiredArgsConstructor
public class Calculator {

    private final SimpleCalculator simpleCalculator;

    @GetMapping("/{a}/{b}/{operation}")
    @ResponseStatus(HttpStatus.OK)
    public CalculationResultDto calculate(@PathVariable int a, @PathVariable int b, @PathVariable Operation operation) {
        return this.simpleCalculator.calculate(a, b, operation);
    }

    @GetMapping
    public Iterable<CalculationDto> getHistory() {
        return this.simpleCalculator.getCalculationHistory();
    }
}
