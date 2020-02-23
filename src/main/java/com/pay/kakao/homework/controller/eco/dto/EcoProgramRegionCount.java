package com.pay.kakao.homework.controller.eco.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EcoProgramRegionCount {
    public String region;
    private int count;
}
