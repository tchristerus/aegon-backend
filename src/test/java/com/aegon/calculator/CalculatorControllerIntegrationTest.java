package com.aegon.calculator;

import com.aegon.calculator.model.dto.NewCalculationDto;
import com.aegon.calculator.repository.CalculationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CalculationRepository calculationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void cleanDb() {
        this.calculationRepository.deleteAll();
        assertEquals(0, this.calculationRepository.count());

    }

    @Test
    void calculatorAdd() throws Exception {
        NewCalculationDto calculationDto = new NewCalculationDto(1, 1);
        this.mvc.perform(post("/api/calculation/ADD")
                .content(this.objectMapper.writeValueAsString(calculationDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":2.0}"));

        assertEquals(1, this.calculationRepository.count());
    }

    @Test
    void calculatorSubtract() throws Exception {
        NewCalculationDto calculationDto = new NewCalculationDto(3, 1);
        this.mvc.perform(post("/api/calculation/SUBTRACT")
                .content(this.objectMapper.writeValueAsString(calculationDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":2.0}"));

        assertEquals(1, this.calculationRepository.count());
    }

    @Test
    void calculatorDivide() throws Exception {
        NewCalculationDto calculationDto = new NewCalculationDto(4, 2);
        this.mvc.perform(post("/api/calculation/DIVIDE")
                .content(this.objectMapper.writeValueAsString(calculationDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":2.0}"));

        assertEquals(1, this.calculationRepository.count());

        calculationDto = new NewCalculationDto(4, 0);
        this.mvc.perform(post("/api/calculation/DIVIDE")
                .content(this.objectMapper.writeValueAsString(calculationDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isLoopDetected())
                .andExpect(content().string("{\"error\":\"Unable to divide by zero\"}"));

        assertEquals(1, this.calculationRepository.count());
    }

    @Test
    void calculatorMultiply() throws Exception {
        NewCalculationDto calculationDto = new NewCalculationDto(1, 2);
        this.mvc.perform(post("/api/calculation/MULTIPLY")
                .content(this.objectMapper.writeValueAsString(calculationDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":2.0}"));

        assertEquals(1, this.calculationRepository.count());
    }
}
