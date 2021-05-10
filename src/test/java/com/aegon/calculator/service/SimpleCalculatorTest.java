package com.aegon.calculator.service;

import com.aegon.calculator.enums.Operation;
import com.aegon.calculator.exception.response.DivideByZeroException;
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
class SimpleCalculatorTest {

    @InjectMocks
    private SimpleCalculator simpleCalculator;

    @Mock
    CalculationRepository calculationRepository;

    @Test
    void testAdd() {
        Mockito.when(this.calculationRepository.save(any())).thenReturn(null);
        assertEquals(2.0, this.simpleCalculator.calculate(1, 1, Operation.ADD).getResult());
        verify(this.calculationRepository, times(1)).save(any());
    }

    @Test
    void testSubtract() {
        Mockito.when(this.calculationRepository.save(any())).thenReturn(null);
        assertEquals(2.0, this.simpleCalculator.calculate(3, 1, Operation.SUBTRACT).getResult());
        verify(this.calculationRepository, times(1)).save(any());
    }

    @Test
    void testDivide() {
        Mockito.when(this.calculationRepository.save(any())).thenReturn(null);
        assertEquals(2.0, this.simpleCalculator.calculate(2, 1, Operation.DIVIDE).getResult());
        assertEquals(0.5, this.simpleCalculator.calculate(5, 10, Operation.DIVIDE).getResult());

        verify(this.calculationRepository, times(2)).save(any());

        assertThrows(DivideByZeroException.class, () -> this.simpleCalculator.calculate(2, 0, Operation.DIVIDE));
    }

    @Test
    void testMultiply() {
        Mockito.when(this.calculationRepository.save(any())).thenReturn(null);
        assertEquals(2.0, this.simpleCalculator.calculate(1, 2, Operation.MULTIPLY).getResult());
        verify(this.calculationRepository, times(1)).save(any());
    }
}