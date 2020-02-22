package com.pay.kakao.homework.service.ecological;

import com.pay.kakao.homework.entity.ecological.EcologicalProgram;
import com.pay.kakao.homework.entity.ecological.ProgramThemes;
import com.pay.kakao.homework.enums.EcologicalProgramTheme;
import com.pay.kakao.homework.repository.ecological.EcologicalProgramRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EcologicalServiceTest {
    @Autowired
    private EcologicalService ecologicalService;
    @Autowired
    private EcologicalProgramRepository ecologicalProgramRepository;

    @AfterEach
    void cleanup() {
        ecologicalProgramRepository.deleteAll();
    }

    @Test
    @DisplayName("프로그램 데이터 저장")
    void saveProgramData() {
        List<EcologicalProgram> programs = new ArrayList<>();
        List<ProgramThemes> themes = new ArrayList<>();

        themes.add(ProgramThemes.builder()
                .theme(EcologicalProgramTheme.COAST_ECOLOGICAL)
                .build());
        themes.add(ProgramThemes.builder()
                .theme(EcologicalProgramTheme.FEELING)
                .build());
        themes.add(ProgramThemes.builder()
                .theme(EcologicalProgramTheme.CULTURE_ECOLOGICAL_EXPERIENCE)
                .build());

        programs.add(EcologicalProgram.builder()
                .name("name1")
                .serviceRegion("region1")
                .summary("summary1")
                .description("desc1")
                .themes(themes)
                .build());
        programs.add(EcologicalProgram.builder()
                .name("name2")
                .serviceRegion("region2")
                .summary("summary2")
                .description("desc2")
                .themes(themes)
                .build());

        List<EcologicalProgram> result = ecologicalService.saveProgramData(programs);

        assertEquals(2, result.size());
        assertEquals("name1", result.get(0).getName());
        assertEquals("name2", result.get(1).getName());
        assertEquals(3, result.get(0).getThemes().size());
        assertEquals(EcologicalProgramTheme.COAST_ECOLOGICAL, result.get(0).getThemes().get(0).getTheme());
        assertEquals(EcologicalProgramTheme.FEELING, result.get(1).getThemes().get(1).getTheme());
    }
}