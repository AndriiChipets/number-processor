package com.portaone.student_andrii_chipets.service.reader;

import org.springframework.web.multipart.MultipartFile;

public interface FileReader {

    int[] getNumbersFromFile(MultipartFile file);

}
