package com.portaone.student_andrii_chipets.service;

import com.portaone.student_andrii_chipets.dto.NumbersStatisticDto;
import org.springframework.web.multipart.MultipartFile;

public interface NumbersService {

    NumbersStatisticDto processNumbers(MultipartFile file);
}
