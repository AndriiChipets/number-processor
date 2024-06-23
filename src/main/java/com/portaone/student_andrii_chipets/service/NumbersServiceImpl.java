package com.portaone.student_andrii_chipets.service;

import com.portaone.student_andrii_chipets.domain.NumbersStatistic;
import com.portaone.student_andrii_chipets.dto.NumbersStatisticDto;
import com.portaone.student_andrii_chipets.mapper.NumbersStatisticMapper;
import com.portaone.student_andrii_chipets.service.processor.NumberProcessor;
import com.portaone.student_andrii_chipets.service.reader.FileReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class NumbersServiceImpl implements NumbersService {

    private final FileReader fileReader;
    private final NumberProcessor processor;

    @Override
    public NumbersStatisticDto processNumbers(MultipartFile file) {
        long start = System.currentTimeMillis();
        int[] numbers = fileReader.getNumbersFromFile(file);
        NumbersStatistic numbersStatistic = NumbersStatistic.builder()
                .withMin(processor.findMin(numbers))
                .withMax(processor.findMax(numbers))
                .withMedian(processor.findMedian(numbers))
                .withAverage(processor.calcAvg(numbers))
                .withLongestAscNumSequence(processor.findLongestAscendingSequence(numbers))
                .withLongestDescNumSequence(processor.findLongestDescendingSequence(numbers))
                .withTotalNumQuantity(processor.getTotalNumQuantity(numbers))
                .build();
        long end = System.currentTimeMillis();
        numbersStatistic.setTotalProcessingTime(processor.calcProcessTime(start, end));
        return NumbersStatisticMapper.mapToNumStatDto(numbersStatistic);
    }
}
