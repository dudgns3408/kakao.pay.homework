package com.pay.kakao.homework.controller.ecological;

import com.pay.kakao.homework.controller.ecological.dto.EcologicalProgramDto;
import com.pay.kakao.homework.controller.ecological.mapper.EcologicalProgramMapper;
import com.pay.kakao.homework.service.ecological.EcologicalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ecological")
@RequiredArgsConstructor
public class EcologicalController {
    private final EcologicalService ecologicalService;
    private final EcologicalProgramMapper ecologicalProgramMapper;

    @GetMapping("/program/{id}")
    public ResponseEntity getProgram(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(ecologicalProgramMapper.entityToDto(ecologicalService.findById(id)));
    }

    @PostMapping("/program")
    public ResponseEntity postProgram(@RequestBody EcologicalProgramDto ecologicalProgramDto) {
        return ResponseEntity
                .ok()
                .body(ecologicalProgramMapper.entityToDto(
                        ecologicalService.insertProgram(ecologicalProgramMapper.dtoToEntity(ecologicalProgramDto))));
    }

    @PutMapping("/program")
    public ResponseEntity putEcologicalProgram(@RequestBody EcologicalProgramDto ecologicalProgramDto) {
        return ResponseEntity
                .ok()
                .body(ecologicalProgramMapper.entityToDto(
                        ecologicalService.modifyProgram(ecologicalProgramMapper.dtoToEntity(ecologicalProgramDto))));
    }

    @GetMapping("/programs/{region}")
    public ResponseEntity getProgramsByRegion(@PathVariable String region) {
        return null;
    }

    @GetMapping("/programs/summary/{keyword}/region/count")
    public ResponseEntity getProgramRegionCountBySummary(@PathVariable String keyword) {
        return null;
    }

    @GetMapping("/programs/description/{keyword}/count")
    public ResponseEntity getKeywordCountByDescription(@PathVariable String keyword) {
        return null;
    }

    //todo :: 옵션 문제
    @GetMapping("/program/recommendation")
    public ResponseEntity getRecommendProgram() {
        return null;
    }
}
