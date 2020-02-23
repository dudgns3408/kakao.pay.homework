package com.pay.kakao.homework.controller.ecological;

import com.pay.kakao.homework.controller.ecological.dto.EcologicalProgramFileDto;
import com.pay.kakao.homework.controller.ecological.mapper.EcologicalProgramMapper;
import com.pay.kakao.homework.service.ecological.EcologicalService;
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
@RequestMapping("/api/ecological/file")
@RequiredArgsConstructor
public class EcologicalFileController {
    private final EcologicalService ecologicalService;
    private final EcologicalProgramMapper ecologicalProgramMapper;

    @PostMapping("read")
    public ResponseEntity readEcologicalList(@RequestParam("file") MultipartFile file) {
        List<EcologicalProgramFileDto> data;

        data = CSVUtils.convertMultipartFileToBeanList(
                file
                , EcologicalProgramFileDto.class
                , new String[]{"id","name","themes","serviceRegion","summary","description"});

        ecologicalService.saveProgramData(ecologicalProgramMapper.fileDtoListToEntityList(data));

        return ResponseEntity.ok().build();
    }


}
