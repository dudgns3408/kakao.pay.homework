package com.pay.kakao.homework.service.eco;

import com.pay.kakao.homework.entity.eco.EcoProgram;
import com.pay.kakao.homework.enums.EcoProgramTheme;
import com.pay.kakao.homework.exception.HomeworkException;
import com.pay.kakao.homework.repository.eco.EcoProgramRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EcoServiceTest {
    @Autowired
    private EcoService ecoService;
    @Autowired
    private EcoProgramRepository ecologicalProgramRepository;

    private List<EcoProgram> programs;
    @BeforeEach
    void setUp() {
        programs = new ArrayList<>();

        EcoProgram ecoProgram = EcoProgram.builder()
                .name("name1")
                .serviceRegion("region1")
                .summary("summary1")
                .description("desc1")
                .themes(Arrays.asList(
                        EcoProgramTheme.COAST_ECOLOGICAL.name()
                        , EcoProgramTheme.FEELING.name()
                        , EcoProgramTheme.CULTURE_ECOLOGICAL_EXPERIENCE.name()
                        ))
                .build();
        programs.add(ecoProgram);

        ecoProgram = EcoProgram.builder()
                .name("name2")
                .serviceRegion("region2")
                .summary("summary2")
                .description("desc2")
                .themes(Arrays.asList(
                        EcoProgramTheme.CHILD_YOUTH_EXPERIENCE.name()
                        , EcoProgramTheme.HISTORY_CULTURE.name()
                        , EcoProgramTheme.NATURE_ECOLOGICAL_EXPERIENCE.name()
                ))
                .build();

        programs.add(ecoProgram);
    }

    @AfterEach
    void cleanup() {
        ecologicalProgramRepository.deleteAll();
    }

    @Test
    @DisplayName("프로그램 데이터리스트 저장")
    void saveProgramData() {
        List<EcoProgram> result = ecoService.saveProgramData(programs);

        assertAll(
                () -> assertEquals(2, result.size())
                , () -> assertEquals("name1", result.get(0).getName())
                , () -> assertEquals("name2", result.get(1).getName())
                , () -> assertEquals(3, result.get(0).getThemes().size())
                , () -> assertEquals(EcoProgramTheme.COAST_ECOLOGICAL.name(), result.get(0).getThemes().get(0))
                , () -> assertEquals(EcoProgramTheme.HISTORY_CULTURE.name(), result.get(1).getThemes().get(1))
        );
    }

    @Test
    @DisplayName("프로그램 데이터 저장")
    void insertProgram() {
        EcoProgram ecoProgram = ecoService.insertProgram(programs.get(0));

        assertAll(
                () -> assertNotNull(ecoProgram)
                , () -> assertNotNull(ecoService.findById(ecoProgram.getId()))
                , () -> assertNotNull(ecoService.findById(ecoProgram.getId()).getThemes().get(0))
        );
    }

    @Test
    @DisplayName("프로그램 데이터 조회")
    void findById() {
        EcoProgram ecoProgram = ecoService.insertProgram(programs.get(0));

        assertAll(
                () -> assertNotNull(ecoService.findById(ecoProgram.getId()))
                , () -> assertNotNull(ecoService.findById(ecoProgram.getId()).getThemes().get(0))
                , () -> assertThrows(HomeworkException.class, () -> ecoService.findById(1000L))
        );
    }

    @Test
    @DisplayName("프로그램 데이터 수정")
    void modifyProgram() {
        EcoProgram ecoProgram = ecoService.insertProgram(programs.get(0));

        EcoProgram modifyEcoProgram =
                EcoProgram.builder()
                        .id(ecoProgram.getId())
                        .name("modifiedName")
                        .themes(ecoProgram.getThemes())
                        .description(ecoProgram.getDescription())
                        .summary(ecoProgram.getSummary())
                        .serviceRegion(ecoProgram.getServiceRegion())
                        .build();

        modifyEcoProgram.setThemes(Arrays.asList(EcoProgramTheme.FOREST_CURE.name()));

        EcoProgram modifiedProgram = ecoService.modifyProgram(modifyEcoProgram);

        assertAll(
                () -> assertEquals(ecoProgram.getId(), modifiedProgram.getId())
                , () -> assertEquals(ecoProgram.getDescription(), modifiedProgram.getDescription())
                , () -> assertEquals("modifiedName", modifiedProgram.getName())
                , () -> assertNotNull(modifiedProgram.getThemes().get(0))
                , () -> assertEquals(EcoProgramTheme.FOREST_CURE.name(), modifiedProgram.getThemes().get(0))
        );
    }
}