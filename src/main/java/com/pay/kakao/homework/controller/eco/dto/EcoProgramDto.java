package com.pay.kakao.homework.controller.eco.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EcoProgramDto {
    private String id;
    private String name;
    private String[] themes;
    private String serviceRegion;
    private String summary;
    private String description;
}
