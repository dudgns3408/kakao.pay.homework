//package com.pay.kakao.homework.controller.ecological.mapper;
//
//import com.pay.kakao.homework.entity.ecological.ProgramThemes;
//import com.pay.kakao.homework.enums.EcologicalProgramTheme;
//import io.micrometer.core.instrument.util.StringUtils;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class ProgramThemesMapper {
//    public List<ProgramThemes> commaSeparatedEnumDescStringToEntityList(String str) {
//        List<ProgramThemes> programThemes = new ArrayList<>();
//        String[] arr = str.split(",");
//
//        for (String enumDesc : arr) {
//            if (validate(enumDesc))
//                programThemes.add(enumNameStringToEntity(enumDesc));
//        }
//
//        return programThemes;
//    }
//
//    public ProgramThemes enumNameStringToEntity(String enumDesc) {
//        return ProgramThemes.builder()
//                .theme(EcologicalProgramTheme.of(enumDesc))
//                .build();
//    }
//
//    private boolean validate(String enumDesc) {
//        return StringUtils.isNotBlank(enumDesc);
//    }
//
//    public String entityListToEnumDescString(List<ProgramThemes> programThemesList) {
//        StringBuilder stringBuilder = new StringBuilder();
//
////        for (ProgramThemes programThemes : programThemesList) {
//        for ( int i = 0; i < programThemesList.size(); i++) {
//            stringBuilder.append(programThemesList.get(i).getTheme().getDescription());
//            if (i != programThemesList.size()-1) {
//                stringBuilder.append(",");
//            }
//        }
//
//        return stringBuilder.toString();
//    }
//}
