package com.aegon.calculator.dto;

import com.aegon.calculator.enums.Operation;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class CalculationDto {
    private final int num1;
    private final int num2;
    private final Operation operation;
    private final double result;
}
