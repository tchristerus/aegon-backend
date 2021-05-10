package com.aegon.calculator.service;

import com.aegon.calculator.dto.CalculationResult;
import com.aegon.calculator.enums.Operation;
import com.aegon.calculator.exception.response.DivideByZeroException;
import org.springframework.stereotype.Service;

@Service
public class SimpleCalculator {

    public CalculationResult calculate(int a, int b, Operation operation) {
        switch (operation) {
            case ADD:
                return new CalculationResult(add(a, b));
            case DIVIDE:
                return new CalculationResult(divide(a, b));
            case MULTIPLY:
                return new CalculationResult(multiply(a, b));
            case SUBTRACT:
                return new CalculationResult(subtract(a, b));
        }
        return null;
    }

    public double add(int a, int b) {
        return a + b;
    }

    public double divide(int a, int b) {
        if (0 == b) {
            throw new DivideByZeroException("Unable to divide by zero");
        }
        return (double) a / b;
    }

    public double subtract(int a, int b) {
        return (double) a - b;
    }

    public double multiply(int a, int b) {
        return (double) a * b;
    }
}
