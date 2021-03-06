package com.pay.kakao.homework.service.eco;

import com.pay.kakao.homework.controller.eco.dto.EcoProgramByRegionDto;
import com.pay.kakao.homework.controller.eco.dto.EcoProgramCountByRegionDto;
import com.pay.kakao.homework.controller.eco.dto.EcoProgramKeywordCount;
import com.pay.kakao.homework.controller.eco.dto.RecommendEcoProgramDto;
import com.pay.kakao.homework.entity.eco.EcoProgram;
import com.pay.kakao.homework.exception.HomeworkException;
import com.pay.kakao.homework.repository.eco.EcoProgramRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EcoService {
    private final EcoProgramRepository ecologicalProgramRepository;

    @Transactional
    public List<EcoProgram> saveProgramData(List<EcoProgram> ecoPrograms) {
        return ecologicalProgramRepository.saveAll(ecoPrograms);
    }

    public EcoProgram findById(Long id) {
        return findByIdThrowException(id);
    }

    private EcoProgram findByIdThrowException(Long id) {
        return ecologicalProgramRepository.findById(id)
                .orElseThrow(() -> new HomeworkException("해당 id의 프로그램을 찾을 수 없습니다. id : " + id, HttpStatus.BAD_REQUEST));
    }

    @Transactional
    public EcoProgram insertProgram(EcoProgram ecoProgram) {
        return ecologicalProgramRepository.save(ecoProgram);
    }

    @Transactional
    public EcoProgram modifyProgram(EcoProgram ecoProgram) {
        EcoProgram existEcoProgram = getExistProgram(ecoProgram.getId());

        existEcoProgram.update(ecoProgram);

        return existEcoProgram;
    }

    private EcoProgram getExistProgram(Long id) {
        return ecologicalProgramRepository.findById(id)
                .orElseThrow(() -> new HomeworkException("수정하려는 프로그램이 존재하지 않습니다. id : " + id, HttpStatus.BAD_REQUEST));
    }

    public EcoProgramByRegionDto getProgramsByRegion(String region) {
        EcoProgramByRegionDto ecoProgramByRegionDto = EcoProgramByRegionDto.builder()
                .region(region)
                .build();
        ecoProgramByRegionDto.addPrograms(findAllByRegionLike(region));

        return ecoProgramByRegionDto;
    }

    private List<EcoProgram> findAllByRegionLike(String region) {
        return ecologicalProgramRepository.findAllByServiceRegionLike(makeMiddleLikeSearchStr(region));
    }

    public EcoProgramCountByRegionDto getProgramCountByRegion(String summary) {
        EcoProgramCountByRegionDto ecoProgramCountByRegionDto = EcoProgramCountByRegionDto.builder()
                .keyword(summary)
                .build();
        ecoProgramCountByRegionDto.addPrograms(getCountByRegionMap(summary));

        return ecoProgramCountByRegionDto;
    }

    private Map<String, Long> getCountByRegionMap(String summary) {
        List<EcoProgram> ecoPrograms = findAllBySummaryLike(summary);

        return ecoPrograms.stream()
                .collect(Collectors.groupingBy(EcoProgram::getServiceRegion, Collectors.counting()));
    }

    private List<EcoProgram> findAllBySummaryLike(String summary) {
        return ecologicalProgramRepository.findAllBySummaryLike(makeMiddleLikeSearchStr(summary));
    }

    public EcoProgramKeywordCount getKeywordCount(String keyword) {
        List<EcoProgram> ecoPrograms = findAllByDescription(keyword);

        return EcoProgramKeywordCount.builder()
                .keyword(keyword)
                .count(calculateKeywordCount(keyword, ecoPrograms))
                .build();
    }

    private int calculateKeywordCount(String keyword, List<EcoProgram> ecoPrograms) {
        return ecoPrograms.stream()
                .map(EcoProgram::getDescription)
                .filter(s -> s.contains(keyword))
                .mapToInt(s -> StringUtils.countMatches(s, keyword))
                .sum();
    }

    private List<EcoProgram> findAllByDescription(String keyword) {
        return ecologicalProgramRepository.findAllByDescriptionLike(makeMiddleLikeSearchStr(keyword));
    }

    public RecommendEcoProgramDto getRecommendProgram(String region, String keyword) {
        return RecommendEcoProgramDto.builder()
                .program(findRecommendProgramId(region, keyword))
                .build();
    }

    private String findRecommendProgramId(String region, String keyword) {
        List<EcoProgram> ecoPrograms = getRegionOrKeywordContainEcoProgram(region, keyword);

        return findBestScoreEcoProgramId(ecoPrograms, keyword);
    }

    private String findBestScoreEcoProgramId(List<EcoProgram> ecoPrograms, String keyword) {
        return ecoPrograms.stream()
                .max(Comparator.comparingInt(ecoProgram -> ecoProgram.calculateScore(keyword)))
                .map(EcoProgram::getId)
                .map(String::valueOf)
                .orElse("");
    }

    private List<EcoProgram> getRegionOrKeywordContainEcoProgram(String region, String keyword) {
        return ecologicalProgramRepository
                .searchByRegionAndKeyword(makeMiddleLikeSearchStr(region), makeMiddleLikeSearchStr(keyword));
    }

    private String makeMiddleLikeSearchStr(String word) {
        return "%" + word + "%";
    }
}
