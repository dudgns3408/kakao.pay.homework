package com.pay.kakao.homework.controller.eco;

import com.pay.kakao.homework.controller.eco.dto.EcoProgramByRegionDto;
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
        //fixme :: region을 코드로 변경하는 작업 필요
        EcoProgramByRegionDto ecoProgramByRegionDto = EcoProgramByRegionDto.builder()
                .region(region)
                .build();
        ecoProgramByRegionDto.addPrograms(ecoService.findAllByRegion(region));

        return ResponseEntity
                .ok()
                .body(ecoProgramByRegionDto);
    }

    @GetMapping("programs/summary/{keyword}/region/count")
    public ResponseEntity getProgramRegionCountBySummary(@PathVariable String keyword) {
        return null;
    }

    @GetMapping("programs/description/{keyword}/count")
    public ResponseEntity getKeywordCountByDescription(@PathVariable String keyword) {
        return null;
    }

    //todo :: 옵션 문제
    @GetMapping("program/recommendation")
    public ResponseEntity getRecommendProgram() {
        return null;
    }
}
