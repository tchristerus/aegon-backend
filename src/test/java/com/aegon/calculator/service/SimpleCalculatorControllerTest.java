package com.aegon.calculator.service;

import com.aegon.calculator.exception.response.DivideByZeroException;
import com.aegon.calculator.model.dto.NewCalculationDto;
import com.aegon.calculator.model.enums.Operation;
import com.aegon.calculator.repository.CalculationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class SimpleCalculatorControllerTest {

    @InjectMocks
    private SimpleCalculator simpleCalculator;

    @Mock
    CalculationRepository calculationRepository;

    @Test
    void testAdd() {
        Mockito.when(this.calculationRepository.save(any())).thenReturn(null);
        NewCalculationDto newCalculationDto = new NewCalculationDto(1, 1);
        assertEquals(2.0, this.simpleCalculator.calculate(newCalculationDto, Operation.ADD).getResult());
        verify(this.calculationRepository, times(1)).save(any());
    }

    @Test
    void testSubtract() {
        Mockito.when(this.calculationRepository.save(any())).thenReturn(null);
        NewCalculationDto newCalculationDto = new NewCalculationDto(3, 1);
        assertEquals(2.0, this.simpleCalculator.calculate(newCalculationDto, Operation.SUBTRACT).getResult());
        verify(this.calculationRepository, times(1)).save(any());
    }

    @Test
    void testDivide() {
        Mockito.when(this.calculationRepository.save(any())).thenReturn(null);
        NewCalculationDto newCalculationDto = new NewCalculationDto(2, 1);
        assertEquals(2.0, this.simpleCalculator.calculate(newCalculationDto, Operation.DIVIDE).getResult());

        newCalculationDto = new NewCalculationDto(5, 10);
        assertEquals(0.5, this.simpleCalculator.calculate(newCalculationDto, Operation.DIVIDE).getResult());

        verify(this.calculationRepository, times(2)).save(any());

        assertThrows(DivideByZeroException.class, () -> {
            NewCalculationDto calculationDto = new NewCalculationDto(2, 0);
            this.simpleCalculator.calculate(calculationDto, Operation.DIVIDE);
        });
    }

    @Test
    void testMultiply() {
        Mockito.when(this.calculationRepository.save(any())).thenReturn(null);
        NewCalculationDto newCalculationDto = new NewCalculationDto(1, 2);
        assertEquals(2.0, this.simpleCalculator.calculate(newCalculationDto, Operation.MULTIPLY).getResult());
        verify(this.calculationRepository, times(1)).save(any());
    }
}