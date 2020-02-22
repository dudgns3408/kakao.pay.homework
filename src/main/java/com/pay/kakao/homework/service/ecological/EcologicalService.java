package com.pay.kakao.homework.service.ecological;

import com.pay.kakao.homework.entity.ecological.EcologicalProgram;
import com.pay.kakao.homework.repository.ecological.EcologicalProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EcologicalService {
    private final EcologicalProgramRepository ecologicalProgramRepository;
    public List<EcologicalProgram> saveProgramData(List<EcologicalProgram> ecologicalPrograms) {
        return ecologicalProgramRepository.saveAll(ecologicalPrograms);
    }
}
