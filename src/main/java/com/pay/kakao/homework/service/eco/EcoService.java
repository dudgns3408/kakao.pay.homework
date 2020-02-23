package com.pay.kakao.homework.service.eco;

import com.pay.kakao.homework.entity.eco.EcoProgram;
import com.pay.kakao.homework.exception.HomeworkException;
import com.pay.kakao.homework.repository.eco.EcoProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    public List<EcoProgram> findAllByRegion(String region) {
        return ecologicalProgramRepository.findAllByServiceRegionLike("%" + region + "%");
    }
}
