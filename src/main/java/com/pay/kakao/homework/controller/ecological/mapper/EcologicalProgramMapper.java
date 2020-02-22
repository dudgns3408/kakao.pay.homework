package com.pay.kakao.homework.controller.ecological.mapper;

import com.pay.kakao.homework.controller.ecological.dto.EcologicalProgramDto;
import com.pay.kakao.homework.entity.ecological.EcologicalProgram;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EcologicalProgramMapper {
    private final ProgramThemesMapper programThemesMapper;

    public List<EcologicalProgram> dtoListToEntityList(List<EcologicalProgramDto> list) {
        List<EcologicalProgram> ecologicalPrograms = new ArrayList<>();

        list.forEach(ecologicalProgramDto -> ecologicalPrograms.add(dtoToEntity(ecologicalProgramDto)));

        return ecologicalPrograms;
    }

    public EcologicalProgram dtoToEntity(EcologicalProgramDto ecologicalProgramDto) {
        return EcologicalProgram.builder()
                .name(ecologicalProgramDto.getName())
                .description(ecologicalProgramDto.getDescription())
                .serviceRegion(ecologicalProgramDto.getServiceRegion())
                .summary(ecologicalProgramDto.getSummary())
                .themes(programThemesMapper.commaSeparatedEnumDescStringToEntityList(ecologicalProgramDto.getThemes()))
                .build();
    }
}
