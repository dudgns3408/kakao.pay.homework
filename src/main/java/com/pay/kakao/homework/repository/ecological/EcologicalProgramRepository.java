package com.pay.kakao.homework.repository.ecological;

import com.pay.kakao.homework.entity.ecological.EcologicalProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EcologicalProgramRepository extends JpaRepository<EcologicalProgram, Long>, CustomEcologicalProgramRepository {
}
