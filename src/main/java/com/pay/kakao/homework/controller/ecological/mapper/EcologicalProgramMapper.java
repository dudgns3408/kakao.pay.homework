package com.pay.kakao.homework.controller.ecological.mapper;

import com.pay.kakao.homework.controller.ecological.dto.EcologicalProgramDto;
import com.pay.kakao.homework.controller.ecological.dto.EcologicalProgramFileDto;
import com.pay.kakao.homework.entity.ecological.EcologicalProgram;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EcologicalProgramMapper {
//    private final ProgramThemesMapper programThemesMapper;

    public List<EcologicalProgram> fileDtoListToEntityList(List<EcologicalProgramFileDto> list) {
        List<EcologicalProgram> ecologicalPrograms = new ArrayList<>();

        list.forEach(ecologicalProgramFileDto -> ecologicalPrograms.add(fileDtoToEntity(ecologicalProgramFileDto)));

        return ecologicalPrograms;
    }

    public List<EcologicalProgram> dtoListToEntityList(List<EcologicalProgramDto> list) {
        List<EcologicalProgram> ecologicalPrograms = new ArrayList<>();

        list.forEach(ecologicalProgramDto -> ecologicalPrograms.add(dtoToEntity(ecologicalProgramDto)));

        return ecologicalPrograms;
    }

    public EcologicalProgram fileDtoToEntity(EcologicalProgramFileDto ecologicalProgramFileDto) {
        return EcologicalProgram.builder()
                .name(ecologicalProgramFileDto.getName())
                .description(ecologicalProgramFileDto.getDescription())
                .serviceRegion(ecologicalProgramFileDto.getServiceRegion())
                .summary(ecologicalProgramFileDto.getSummary())
                .themes(Arrays.asList(ecologicalProgramFileDto.getThemes().split(",")))
                .build();
    }

    public EcologicalProgram dtoToEntity(EcologicalProgramDto ecologicalProgramDto) {
        return EcologicalProgram.builder()
                .id(ecologicalProgramDto.getId() == null ? null : Long.parseLong(ecologicalProgramDto.getId()))
                .name(ecologicalProgramDto.getName())
                .description(ecologicalProgramDto.getDescription())
                .serviceRegion(ecologicalProgramDto.getServiceRegion())
                .summary(ecologicalProgramDto.getSummary())
                .themes(Arrays.asList(ecologicalProgramDto.getThemes()))
                .build();
    }

    public EcologicalProgramDto entityToDto(EcologicalProgram ecologicalProgram) {
        return EcologicalProgramDto.builder()
                .id(String.valueOf(ecologicalProgram.getId()))
                .name(ecologicalProgram.getName())
                .description(ecologicalProgram.getDescription())
                .serviceRegion(ecologicalProgram.getServiceRegion())
                .summary(ecologicalProgram.getSummary())
                .themes(ecologicalProgram.getThemes().toArray(new String[0]))
                .build();
    }
}
