package com.pay.kakao.homework.controller.eco.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EcoProgramCountByRegion {
    public String region;
    private long count;
}
