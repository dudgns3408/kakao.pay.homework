package com.pay.kakao.homework.controller.eco;

import com.pay.kakao.homework.controller.eco.dto.EcoProgramFileDto;
import com.pay.kakao.homework.controller.eco.mapper.EcoProgramMapper;
import com.pay.kakao.homework.service.eco.EcoService;
import com.pay.kakao.homework.utils.CSVUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/eco/file")
@RequiredArgsConstructor
public class EcoFileController {
    private final EcoService ecoService;
    private final EcoProgramMapper ecoProgramMapper;

    @PostMapping("read")
    public ResponseEntity readEcologicalList(@RequestParam("file") MultipartFile file) {
        List<EcoProgramFileDto> data;

        data = CSVUtils.convertMultipartFileToBeanList(
                file
                , EcoProgramFileDto.class
                , new String[]{"id","name","themes","serviceRegion","summary","description"});

        ecoService.saveProgramData(ecoProgramMapper.fileDtoListToEntityList(data));

        return ResponseEntity.ok().build();
    }


}
