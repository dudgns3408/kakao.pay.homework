package com.pay.kakao.homework.controller.eco.mapper;

import com.pay.kakao.homework.controller.eco.dto.EcoProgramDto;
import com.pay.kakao.homework.controller.eco.dto.EcoProgramFileDto;
import com.pay.kakao.homework.entity.eco.EcoProgram;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EcoProgramMapper {
    public List<EcoProgram> fileDtoListToEntityList(List<EcoProgramFileDto> list) {
        List<EcoProgram> ecoPrograms = new ArrayList<>();

        list.forEach(ecoProgramFileDto -> ecoPrograms.add(fileDtoToEntity(ecoProgramFileDto)));

        return ecoPrograms;
    }

    public EcoProgram fileDtoToEntity(EcoProgramFileDto ecoProgramFileDto) {
        return EcoProgram.builder()
                .name(StringUtils.trim(ecoProgramFileDto.getName()))
                .description(ecoProgramFileDto.getDescription().trim())
                .serviceRegion(StringUtils.trim(ecoProgramFileDto.getServiceRegion().trim()))
                .summary(ecoProgramFileDto.getSummary())
                .themes(Arrays.asList(StringUtils.trim(ecoProgramFileDto.getThemes()).split(",")))
                .build();
    }

    public EcoProgram dtoToEntity(EcoProgramDto ecoProgramDto) {
        return EcoProgram.builder()
                .id(ecoProgramDto.getId() == null ? null : Long.parseLong(ecoProgramDto.getId()))
                .name(ecoProgramDto.getName())
                .description(ecoProgramDto.getDescription())
                .serviceRegion(ecoProgramDto.getServiceRegion())
                .summary(ecoProgramDto.getSummary())
                .themes(Arrays.asList(ecoProgramDto.getThemes()))
                .build();
    }

    public EcoProgramDto entityToDto(EcoProgram ecoProgram) {
        return EcoProgramDto.builder()
                .id(String.valueOf(ecoProgram.getId()))
                .name(ecoProgram.getName())
                .description(ecoProgram.getDescription())
                .serviceRegion(ecoProgram.getServiceRegion())
                .summary(ecoProgram.getSummary())
                .themes(ecoProgram.getThemes().toArray(new String[0]))
                .build();
    }
}
