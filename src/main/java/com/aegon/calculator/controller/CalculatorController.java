package com.aegon.calculator.controller;

import com.aegon.calculator.model.dto.CalculationDto;
import com.aegon.calculator.model.dto.CalculationResultDto;
import com.aegon.calculator.model.dto.NewCalculationDto;
import com.aegon.calculator.model.enums.Operation;
import com.aegon.calculator.service.SimpleCalculator;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculation")
@RequiredArgsConstructor
public class CalculatorController {

    private final SimpleCalculator simpleCalculator;

    @PostMapping("/{operation}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Executes the requested calculation and returns the result", response = CalculationDto.class, tags = "calculate")
    public CalculationResultDto calculate(@RequestBody NewCalculationDto newCalculationDto, @PathVariable Operation operation) {
        return this.simpleCalculator.calculate(newCalculationDto, operation);
    }

    @GetMapping
    @ApiOperation(value = "Returns all the calculations done so far", response = Iterable.class, tags = "history")
    public Iterable<CalculationDto> getHistory() {
        return this.simpleCalculator.getCalculationHistory();
    }
}
