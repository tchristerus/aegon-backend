package com.aegon.calculator.mapper;

import com.aegon.calculator.dto.CalculationDto;
import com.aegon.calculator.model.Calculation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CalculationDtoMapper {
    CalculationDto toDto(Calculation calculation);

    List<CalculationDto> mapDto(List<Calculation> calculation);
}
