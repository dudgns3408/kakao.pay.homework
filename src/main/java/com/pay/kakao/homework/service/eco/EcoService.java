package com.pay.kakao.homework.service.eco;

import com.pay.kakao.homework.controller.eco.dto.EcoProgramByRegionDto;
import com.pay.kakao.homework.controller.eco.dto.EcoProgramRegionCountDto;
import com.pay.kakao.homework.entity.eco.EcoProgram;
import com.pay.kakao.homework.exception.HomeworkException;
import com.pay.kakao.homework.repository.eco.EcoProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
        ecoProgramByRegionDto.addPrograms(findAllByRegion(region));

        return ecoProgramByRegionDto;
    }

    private List<EcoProgram> findAllByRegion(String region) {
        return ecologicalProgramRepository.findAllByServiceRegionLike("%" + region + "%");
    }

    public EcoProgramRegionCountDto getProgramCountByRegion(String summary) {
        EcoProgramRegionCountDto ecoProgramRegionCountDto = EcoProgramRegionCountDto.builder()
                .keyword(summary)
                .build();
        ecoProgramRegionCountDto.addPrograms(getCountByRegionMap(summary));

        return ecoProgramRegionCountDto;
    }

    private Map<String, Integer> getCountByRegionMap(String summary) {
        List<EcoProgram> ecoPrograms = findAllBySummary(summary);

        Map<String, Integer> countByRegionMap = ecoPrograms.stream().collect(
                Collectors.groupingBy(EcoProgram::getServiceRegion,
                        Collectors.collectingAndThen(
                                Collectors.mapping(EcoProgram::getServiceRegion, Collectors.toList()), List::size
                        ))
        );

        System.out.println(countByRegionMap.get("summary"));

        return countByRegionMap;
    }

    private List<EcoProgram> findAllBySummary(String summary) {
        return ecologicalProgramRepository.findAllBySummaryLike("%" + summary + "%");
    }
}
