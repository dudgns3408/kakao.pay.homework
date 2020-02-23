package com.pay.kakao.homework.repository.eco;

import com.pay.kakao.homework.entity.eco.EcoProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EcoProgramRepository extends JpaRepository<EcoProgram, Long>, CustomEcoProgramRepository {
    List<EcoProgram> findAllByServiceRegionLike(String serviceRegion);

    List<EcoProgram> findAllBySummaryLike(String summary);

    List<EcoProgram> findAllByDescriptionLike(String s);

    List<EcoProgram> findAllByServiceRegionLikeAndThemesLikeOrSummaryLikeOrDescriptionLike(String region, String keyword, String keyword2, String keyword3);
}
