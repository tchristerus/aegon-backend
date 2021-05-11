package com.aegon.calculator;

import com.aegon.calculator.repository.CalculationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CalculationRepository calculationRepository;

    @BeforeEach
    void cleanDb() {
        this.calculationRepository.deleteAll();
        assertEquals(0, this.calculationRepository.count());

    }

    @Test
    void calculatorAdd() throws Exception {

        this.mvc.perform(get("/api/calculation/1/1/ADD")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":2.0}"));

        assertEquals(1, this.calculationRepository.count());
    }

    @Test
    void calculatorSubtract() throws Exception {

        this.mvc.perform(get("/api/calculation/3/1/SUBTRACT")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":2.0}"));

        assertEquals(1, this.calculationRepository.count());
    }

    @Test
    void calculatorDivide() throws Exception {

        this.mvc.perform(get("/api/calculation/4/2/DIVIDE")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":2.0}"));

        assertEquals(1, this.calculationRepository.count());

        this.mvc.perform(get("/api/calculation/4/0/DIVIDE")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isLoopDetected())
                .andExpect(content().string("{\"error\":\"Unable to divide by zero\"}"));

        assertEquals(1, this.calculationRepository.count());
    }

    @Test
    void calculatorMultiply() throws Exception {

        this.mvc.perform(get("/api/calculation/1/2/MULTIPLY")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":2.0}"));

        assertEquals(1, this.calculationRepository.count());
    }
}
