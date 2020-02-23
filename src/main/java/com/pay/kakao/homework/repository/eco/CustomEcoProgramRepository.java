package com.pay.kakao.homework.repository.eco;

import com.pay.kakao.homework.entity.eco.EcoProgram;

import java.util.List;

public interface CustomEcoProgramRepository {
    List<EcoProgram> searchByRegionAndKeyword(String region, String keyword);
}
