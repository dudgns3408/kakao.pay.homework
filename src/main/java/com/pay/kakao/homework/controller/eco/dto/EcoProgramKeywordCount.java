package com.pay.kakao.homework.controller.eco.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EcoProgramKeywordCount {
    private String keyword;
    private int count;
}
