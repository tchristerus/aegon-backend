package com.aegon.calculator.service;

import com.aegon.calculator.exception.response.DivideByZeroException;
import com.aegon.calculator.mapper.CalculationDtoMapper;
import com.aegon.calculator.model.Calculation;
import com.aegon.calculator.model.dto.CalculationDto;
import com.aegon.calculator.model.dto.CalculationResultDto;
import com.aegon.calculator.model.dto.NewCalculationDto;
import com.aegon.calculator.model.enums.Operation;
import com.aegon.calculator.repository.CalculationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service manages all the calculations that can be done with the calculator.
 */
@Service
@RequiredArgsConstructor
public class SimpleCalculator {

    private final CalculationRepository calculationRepository;
    private final CalculationDtoMapper calculationDtoMapper;

    /**
     * Start a calculation with two given numbers and a operation e.g. subtract.
     *
     * @param newCalculationDto containing the numbers required for the calculation
     * @param operation         The type of calculation.
     * @return The outcome of the calculation.
     */
    public CalculationResultDto calculate(NewCalculationDto newCalculationDto, Operation operation) {
        Double result = null;

        switch (operation) {
            case ADD:
                result = add(newCalculationDto.getNum1(), newCalculationDto.getNum2());
                break;
            case DIVIDE:
                result = divide(newCalculationDto.getNum1(), newCalculationDto.getNum2());
                break;
            case MULTIPLY:
                result = multiply(newCalculationDto.getNum1(), newCalculationDto.getNum2());
                break;
            case SUBTRACT:
                result = subtract(newCalculationDto.getNum1(), newCalculationDto.getNum2());
                break;
        }
        persist(newCalculationDto, operation, result);
        return new CalculationResultDto(result);
    }

    /**
     * Fetches all the previous calculation records.
     *
     * @return A list of all history calculations.
     */
    public List<CalculationDto> getCalculationHistory() {
        return this.calculationDtoMapper.mapDto(this.calculationRepository.findAll());
    }

    /**
     * Sums two numbers.
     *
     * @param a The first number
     * @param b The second number to add
     * @return Sum of a and b
     */
    private double add(int a, int b) {
        return a + b;
    }

    /**
     * Divides the given numbers
     *
     * @param a The first number
     * @param b The second number of which the first number should be devided.
     * @return The divided value.
     */
    private double divide(int a, int b) {
        if (0 == b) {
            throw new DivideByZeroException("Unable to divide by zero");
        }
        return (double) a / b;
    }

    /**
     * Subtracts the given numbers
     *
     * @param a The first number
     * @param b The second number
     * @return a subtracted by b
     */
    private double subtract(int a, int b) {
        return (double) a - b;
    }

    /**
     * Multiplies the given numbers
     *
     * @param a The first number
     * @param b The second number that a should be multiplied with
     * @return A multiplied by b
     */
    private double multiply(int a, int b) {
        return (double) a * b;
    }

    /**
     * Saves the calculation to the database for history.
     *
     * @param newCalculationDto containing the numbers required for the calculation
     * @param operation         The type of operation e.g. multiply.
     * @param result            Result of the calculation.
     */
    private void persist(NewCalculationDto newCalculationDto, Operation operation, double result) {
        Calculation calculation = new Calculation();
        calculation.setNum1(newCalculationDto.getNum1());
        calculation.setNum2(newCalculationDto.getNum2());
        calculation.setOperation(operation);
        calculation.setResult(result);
        this.calculationRepository.save(calculation);
    }
}
