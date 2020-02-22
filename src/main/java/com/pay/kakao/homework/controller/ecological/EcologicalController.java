package com.pay.kakao.homework.controller.ecological;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ecological")
public class EcologicalController {
    @GetMapping("/program/{id}")
    public ResponseEntity getProgram(@PathVariable Long id) {
        return null;
    }

    @PostMapping("/program")
    public ResponseEntity postProgram() {
        return null;
    }

    @PutMapping("/program")
    public ResponseEntity putEcologicalProgram() {
        return null;
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
