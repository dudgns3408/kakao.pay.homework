package com.pay.kakao.homework.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.pay.kakao.homework.exception.HomeworkException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CSVUtils {
    public static <T> List<T> convertMultipartFileToBeanList(MultipartFile file, Class<T> tClass, String[] columnMapping) {
        CSVReader csvReader = getCSVReader(file);
        ColumnPositionMappingStrategy<T> strategy = makeColumnPositionMappingStrategy(tClass, columnMapping);

        return parse(csvReader, strategy);
    }

    private static <T> List<T> parse(CSVReader csvReader, ColumnPositionMappingStrategy<T> strategy) {
        List<T> data;
        try {
            data = getMakeCsvToBean(csvReader, strategy).parse();
        } catch (Exception e) {
            throw new HomeworkException("CSV 파일을 변환하는데 실패했습니다.", HttpStatus.BAD_REQUEST);
        }

        return data;
    }

    @NotNull
    private static <T> CsvToBean<T> getMakeCsvToBean(CSVReader csvReader, ColumnPositionMappingStrategy<T> strategy) {
        CsvToBean<T> csvToBean = new CsvToBean<>();
        csvToBean.setCsvReader(csvReader);
        csvToBean.setMappingStrategy(strategy);
        return csvToBean;
    }

    @NotNull
    private static <T> ColumnPositionMappingStrategy<T> makeColumnPositionMappingStrategy(Class<T> tClass, String[] columnMapping) {
        ColumnPositionMappingStrategy<T> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(tClass);
        strategy.setColumnMapping(columnMapping);
        return strategy;
    }

    @NotNull
    private static CSVReader getCSVReader(MultipartFile file) {
        try {
            CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));
            csvReader.skip(1);
            return csvReader;
        } catch (IOException e) {
            throw new HomeworkException("파일을 읽는데 실패했습니다.", HttpStatus.BAD_REQUEST);
        }
    }
}
