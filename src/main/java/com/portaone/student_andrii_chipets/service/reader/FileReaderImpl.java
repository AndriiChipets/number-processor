package com.portaone.student_andrii_chipets.service.reader;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class FileReaderImpl implements FileReader {

    @Override
    public int[] getNumbersFromFile(MultipartFile file) {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            return bufferedReader.lines()
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .toArray();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}
