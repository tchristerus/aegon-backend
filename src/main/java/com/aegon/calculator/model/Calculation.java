package com.aegon.calculator.model;

import com.aegon.calculator.enums.Operation;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int num1;

    private int num2;

    private Operation operation;

    private double result;
}
