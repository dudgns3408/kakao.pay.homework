package com.pay.kakao.homework.service.ecological;

import com.pay.kakao.homework.entity.ecological.EcologicalProgram;
import com.pay.kakao.homework.exception.HomeworkException;
import com.pay.kakao.homework.repository.ecological.EcologicalProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EcologicalService {
    private final EcologicalProgramRepository ecologicalProgramRepository;

    @Transactional
    public List<EcologicalProgram> saveProgramData(List<EcologicalProgram> ecologicalPrograms) {
        return ecologicalProgramRepository.saveAll(ecologicalPrograms);
    }

    public EcologicalProgram findById(Long id) {
        return findByIdThrowException(id);
    }

    private EcologicalProgram findByIdThrowException(Long id) {
        return ecologicalProgramRepository.findById(id)
                .orElseThrow(() -> new HomeworkException("해당 id의 프로그램을 찾을 수 없습니다. id : " + id, HttpStatus.BAD_REQUEST));
    }

    @Transactional
    public EcologicalProgram insertProgram(EcologicalProgram ecologicalProgram) {
        return ecologicalProgramRepository.save(ecologicalProgram);
    }

    @Transactional
    public EcologicalProgram modifyProgram(EcologicalProgram ecologicalProgram) {
        EcologicalProgram existEcologicalProgram = getExistProgram(ecologicalProgram.getId());
//        existEcologicalProgram.getThemes().forEach(programThemes -> programThemes.setEcologicalProgram(existEcologicalProgram));

        existEcologicalProgram.update(ecologicalProgram);

        return existEcologicalProgram;
    }

    private EcologicalProgram getExistProgram(Long id) {
        return ecologicalProgramRepository.findById(id)
                .orElseThrow(() -> new HomeworkException("수정하려는 프로그램이 존재하지 않습니다. id : " + id, HttpStatus.BAD_REQUEST));
    }
}
