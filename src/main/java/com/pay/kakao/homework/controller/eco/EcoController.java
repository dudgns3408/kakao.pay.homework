package com.pay.kakao.homework.controller.eco;

import com.pay.kakao.homework.controller.eco.dto.EcoProgramDto;
import com.pay.kakao.homework.controller.eco.mapper.EcoProgramMapper;
import com.pay.kakao.homework.service.eco.EcoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eco")
@RequiredArgsConstructor
public class EcoController {
    private final EcoService ecoService;
    private final EcoProgramMapper ecoProgramMapper;

    @GetMapping("program/{id}")
    public ResponseEntity getProgram(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(ecoProgramMapper.entityToDto(ecoService.findById(id)));
    }

    @PostMapping("program")
    public ResponseEntity postProgram(@RequestBody EcoProgramDto ecoProgramDto) {
        return ResponseEntity
                .ok()
                .body(ecoProgramMapper.entityToDto(
                        ecoService.insertProgram(ecoProgramMapper.dtoToEntity(ecoProgramDto))));
    }

    @PutMapping("program")
    public ResponseEntity putEcologicalProgram(@RequestBody EcoProgramDto ecoProgramDto) {
        return ResponseEntity
                .ok()
                .body(ecoProgramMapper.entityToDto(
                        ecoService.modifyProgram(ecoProgramMapper.dtoToEntity(ecoProgramDto))));
    }

    @GetMapping("programs/region/contains")
    public ResponseEntity getProgramsByRegion(@RequestParam String region) {
        return ResponseEntity
                .ok()
                .body(ecoService.getProgramsByRegion(region));
    }

    @GetMapping("programs/summary/contains/region/count")
    public ResponseEntity getProgramRegionCountBySummary(@RequestParam String keyword) {
        return ResponseEntity
                .ok()
                .body(ecoService.getProgramCountByRegion(keyword));
    }

    @GetMapping("programs/description/contains/count")
    public ResponseEntity getKeywordCountByDescription(@RequestParam String keyword) {
        return ResponseEntity
                .ok()
                .body(ecoService.getKeywordCount(keyword));
    }

    @GetMapping("program/recommendation")
    public ResponseEntity getRecommendProgram(@RequestParam String region, String keyword) {
        return ResponseEntity
                .ok()
                .body(ecoService.getRecommendProgram(region, keyword));
    }
}
