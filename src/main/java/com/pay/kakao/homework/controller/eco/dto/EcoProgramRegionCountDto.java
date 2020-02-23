package com.pay.kakao.homework.controller.eco.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Builder
@Getter
public class EcoProgramRegionCountDto {
    private String keyword;
    private List<EcoProgramRegionCount> programs;

    public void addPrograms(Map<String, Integer> regionCountMap) {
        if ( programs == null ) {
            programs = new ArrayList<>();
        }

        for (String key : regionCountMap.keySet()) {
            programs.add(EcoProgramRegionCount.builder()
                    .region(key)
                    .count(regionCountMap.get(key))
                    .build());
        }

    }
}