package com.pay.kakao.homework.repository.eco;

import com.pay.kakao.homework.entity.eco.EcoProgram;
import com.pay.kakao.homework.entity.eco.QEcoProgram;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class EcoProgramRepositoryImpl extends QuerydslRepositorySupport implements CustomEcoProgramRepository {
    private QEcoProgram ecoProgram = QEcoProgram.ecoProgram;
    public EcoProgramRepositoryImpl() {
        super(EcoProgram.class);
    }

    @Override
    public List<EcoProgram> searchByRegionAndKeyword(String region, String keyword) {

        return from(ecoProgram)
                .where(ecoProgram.serviceRegion.like(region)
                .and(ecoProgram.themes.contains(keyword)
                        .or(ecoProgram.summary.like(keyword))
                        .or(ecoProgram.description.like(keyword))
                ))
                .fetch();
    }
}
