package com.pay.kakao.homework.enums;

import lombok.Getter;

@Getter
public enum EcoProgramColumnWeight {
    SUMMARY_WEIGHT(3),
    THEME_WEIGHT(2),
    DESCRIPTION_WEIGHT(1);

    private int weight;

    EcoProgramColumnWeight(int weight) {
        this.weight = weight;
    }
}
