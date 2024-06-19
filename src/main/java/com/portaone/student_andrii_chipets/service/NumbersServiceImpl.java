package com.portaone.student_andrii_chipets.service;

import com.portaone.student_andrii_chipets.domain.NumbersStatistic;
import com.portaone.student_andrii_chipets.dto.NumbersStatisticDto;
import com.portaone.student_andrii_chipets.mapper.NumbersStatisticMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

@Service
public class NumbersServiceImpl implements NumbersService {

    @Override
    public NumbersStatisticDto processNumbers(MultipartFile file) {
        long start = System.currentTimeMillis();
        List<Long> numbers = getNumbersFromFile(file);
        NumbersStatistic numbersStatistic = NumbersStatistic.builder()
                .withNumbersFromFile(numbers)
                .withMin(findMin(numbers))
                .withMax(findMax(numbers))
                .withMedian(findMedian(numbers))
                .withAverage(calcAvg(numbers))
                .withLongestAscNumSequence(findLongestAscendingSequence(numbers))
                .withLongestDescNumSequence(findLongestDescendingSequence(numbers))
                .build();
        long end = System.currentTimeMillis();
        numbersStatistic.setTotalProcessingTime(calcProcessTime(start, end));
        return NumbersStatisticMapper.mapToNumStatDto(numbersStatistic);
    }

    public List<Long> getNumbersFromFile(MultipartFile file) {
        List<Long> numbers = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream()) {
            new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .map(String::trim)
                    .map(Long::parseLong)
                    .forEach(numbers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbers;
    }

    public long findMax(List<Long> numbers) {
        return numbers
                .stream()
                .mapToLong(Long::longValue)
                .max().getAsLong();
    }

    public long findMin(List<Long> numbers) {
        return numbers
                .stream()
                .mapToLong(Long::longValue)
                .min().getAsLong();
    }

    public double calcAvg(List<Long> numbers) {
        return numbers.stream()
                .mapToLong(Long::longValue)
                .average().getAsDouble();
    }

    public long findMedian(List<Long> numbers) {
        int quantity = numbers.size();
        return quantity % 2 == 0 ?
                calcMedinaForEvenQuantity(numbers.get(quantity / 2), numbers.get(quantity / 2 - 1))
                : numbers.get(quantity / 2);
    }

    public List<Long> findLongestAscendingSequence(List<Long> numbers) {
        BiFunction<Long, Long, Boolean> isNextLargerThanCurrent = (a, b) -> b > a;
        return findSequence(numbers, isNextLargerThanCurrent);
    }

    public List<Long> findLongestDescendingSequence(List<Long> numbers) {
        BiFunction<Long, Long, Boolean> isNextSmallerThanCurrent = (a, b) -> b < a;
        return findSequence(numbers, isNextSmallerThanCurrent);
    }

    private long calcMedinaForEvenQuantity(long first, long second) {
        return (first + second) / 2;
    }

    private List<Long> findSequence(List<Long> numbers, BiFunction<Long, Long, Boolean> sequenceType) {
        List<Long> numbersSequence = new ArrayList<>();
        int resStart = 0;
        int resEnd = 0;
        int curStart = 0;
        int curEnd = 0;
        for (int i = 0; i < numbers.size() - 1; i++) {
            long currentNum = numbers.get(i);
            long nextNum = numbers.get(i + 1);
            if (sequenceType.apply(currentNum, nextNum)) {
                curEnd = i + 1;
            } else {
                if (resEnd - resStart < curEnd - curStart) {
                    resStart = curStart;
                    resEnd = curEnd;
                }
                curStart = i + 1;
                curEnd = curStart;
            }
        }
        if ((resEnd - resStart) < (curEnd - curStart)) {
            resStart = curStart;
            resEnd = curEnd;
        }
        for (int j = resStart; j <= resEnd; j++) {
            numbersSequence.add(numbers.get(j));
        }
        return numbersSequence;
    }

    public double calcProcessTime(long start, long end) {
        return (double) (end - start) / 1000;
    }
}
