package com.portaone.student_andrii_chipets.service;

import com.portaone.student_andrii_chipets.dto.NumbersStatisticDto;
import org.springframework.web.multipart.MultipartFile;

public interface NumbersService {

    int[] getNumbersFromFile(MultipartFile multipartFile);

    NumbersStatisticDto processNumbers(MultipartFile file);
}
