package com.pay.kakao.homework.controller.eco.dto;


import com.pay.kakao.homework.entity.eco.EcoProgram;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Builder
@Getter
public class EcoProgramByRegionDto {
    private String region;
    private List<EcoProgramByRegion> programs;

    public void addPrograms(List<EcoProgram> ecoPrograms) {
        if ( programs == null ) {
            programs = new ArrayList<>();
        }

        ecoPrograms.forEach(ecoProgram -> programs.add(EcoProgramByRegion.builder()
                .prgmName(ecoProgram.getName())
                .theme(Arrays.toString(ecoProgram.getThemes().toArray()).replace("[", "").replace("]",""))
                .build()));
    }
}

@Getter
@Builder
class EcoProgramByRegion {
    private String prgmName;
    private String theme;
}