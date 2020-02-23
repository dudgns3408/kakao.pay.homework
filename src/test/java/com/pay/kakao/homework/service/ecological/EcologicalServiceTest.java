package com.pay.kakao.homework.service.ecological;

import com.pay.kakao.homework.entity.ecological.EcologicalProgram;
import com.pay.kakao.homework.enums.EcologicalProgramTheme;
import com.pay.kakao.homework.exception.HomeworkException;
import com.pay.kakao.homework.repository.ecological.EcologicalProgramRepository;
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
class EcologicalServiceTest {
    @Autowired
    private EcologicalService ecologicalService;
    @Autowired
    private EcologicalProgramRepository ecologicalProgramRepository;

    private List<EcologicalProgram> programs;
    @BeforeEach
    void setUp() {
        programs = new ArrayList<>();

        EcologicalProgram ecologicalProgram = EcologicalProgram.builder()
                .name("name1")
                .serviceRegion("region1")
                .summary("summary1")
                .description("desc1")
                .themes(Arrays.asList(
                        EcologicalProgramTheme.COAST_ECOLOGICAL.name()
                        , EcologicalProgramTheme.FEELING.name()
                        , EcologicalProgramTheme.CULTURE_ECOLOGICAL_EXPERIENCE.name()
                        ))
                .build();
        programs.add(ecologicalProgram);

        ecologicalProgram = EcologicalProgram.builder()
                .name("name2")
                .serviceRegion("region2")
                .summary("summary2")
                .description("desc2")
                .themes(Arrays.asList(
                        EcologicalProgramTheme.CHILD_YOUTH_EXPERIENCE.name()
                        , EcologicalProgramTheme.HISTORY_CULTURE.name()
                        , EcologicalProgramTheme.NATURE_ECOLOGICAL_EXPERIENCE.name()
                ))
                .build();

        programs.add(ecologicalProgram);
    }

    @AfterEach
    void cleanup() {
        ecologicalProgramRepository.deleteAll();
    }

    @Test
    @DisplayName("프로그램 데이터리스트 저장")
    void saveProgramData() {
        List<EcologicalProgram> result = ecologicalService.saveProgramData(programs);

        assertAll(
                () -> assertEquals(2, result.size())
                , () -> assertEquals("name1", result.get(0).getName())
                , () -> assertEquals("name2", result.get(1).getName())
                , () -> assertEquals(3, result.get(0).getThemes().size())
                , () -> assertEquals(EcologicalProgramTheme.COAST_ECOLOGICAL.name(), result.get(0).getThemes().get(0))
                , () -> assertEquals(EcologicalProgramTheme.HISTORY_CULTURE.name(), result.get(1).getThemes().get(1))
        );
    }

    @Test
    @DisplayName("프로그램 데이터 저장")
    void insertProgram() {
        EcologicalProgram ecologicalProgram = ecologicalService.insertProgram(programs.get(0));

        assertAll(
                () -> assertNotNull(ecologicalProgram)
                , () -> assertNotNull(ecologicalService.findById(ecologicalProgram.getId()))
                , () -> assertNotNull(ecologicalService.findById(ecologicalProgram.getId()).getThemes().get(0))
        );
    }

    @Test
    @DisplayName("프로그램 데이터 조회")
    void findById() {
        EcologicalProgram ecologicalProgram = ecologicalService.insertProgram(programs.get(0));

        assertAll(
                () -> assertNotNull(ecologicalService.findById(ecologicalProgram.getId()))
                , () -> assertNotNull(ecologicalService.findById(ecologicalProgram.getId()).getThemes().get(0))
                , () -> assertThrows(HomeworkException.class, () -> ecologicalService.findById(1000L))
        );
    }

    @Test
    @DisplayName("프로그램 데이터 수정")
    void modifyProgram() {
        EcologicalProgram ecologicalProgram = ecologicalService.insertProgram(programs.get(0));

        EcologicalProgram modifyEcologicalProgram =
                EcologicalProgram.builder()
                        .id(ecologicalProgram.getId())
                        .name("modifiedName")
                        .themes(ecologicalProgram.getThemes())
                        .description(ecologicalProgram.getDescription())
                        .summary(ecologicalProgram.getSummary())
                        .serviceRegion(ecologicalProgram.getServiceRegion())
                        .build();

        modifyEcologicalProgram.setThemes(Arrays.asList(EcologicalProgramTheme.FOREST_CURE.name()));

        EcologicalProgram modifiedProgram = ecologicalService.modifyProgram(modifyEcologicalProgram);

        assertAll(
                () -> assertEquals(ecologicalProgram.getId(), modifiedProgram.getId())
                , () -> assertEquals(ecologicalProgram.getDescription(), modifiedProgram.getDescription())
                , () -> assertEquals("modifiedName", modifiedProgram.getName())
                , () -> assertNotNull(modifiedProgram.getThemes().get(0))
                , () -> assertEquals(EcologicalProgramTheme.FOREST_CURE.name(), modifiedProgram.getThemes().get(0))
        );
    }
}