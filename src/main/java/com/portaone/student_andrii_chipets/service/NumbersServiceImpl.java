package com.portaone.student_andrii_chipets.service;

import com.portaone.student_andrii_chipets.domain.NumbersStatistic;
import com.portaone.student_andrii_chipets.dto.NumbersStatisticDto;
import com.portaone.student_andrii_chipets.mapper.NumbersStatisticMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
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
        List<Integer> numbers = getNumbersFromFile(file);
        NumbersStatistic numbersStatistic = NumbersStatistic.builder()
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

    public List<Integer> getNumbersFromFile(MultipartFile file) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            bufferedReader.lines()
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .forEach(numbers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbers;
    }

    public Integer findMax(List<Integer> numbers) {
        return numbers
                .stream()
                .mapToInt(Integer::intValue)
                .max().getAsInt();
    }

    public Integer findMin(List<Integer> numbers) {
        return numbers
                .stream()
                .mapToInt(Integer::intValue)
                .min().getAsInt();
    }

    public double calcAvg(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .average().getAsDouble();
    }

    public Integer findMedian(List<Integer> numbers) {
        int quantity = numbers.size();
        return quantity % 2 == 0 ?
                calcMedinaForEvenQuantity(numbers.get(quantity / 2), numbers.get(quantity / 2 - 1))
                : numbers.get(quantity / 2);
    }

    public List<Integer> findLongestAscendingSequence(List<Integer> numbers) {
        BiFunction<Integer, Integer, Boolean> isNextLargerThanCurrent = (a, b) -> b > a;
        return findSequence(numbers, isNextLargerThanCurrent);
    }

    public List<Integer> findLongestDescendingSequence(List<Integer> numbers) {
        BiFunction<Integer, Integer, Boolean> isNextSmallerThanCurrent = (a, b) -> b < a;
        return findSequence(numbers, isNextSmallerThanCurrent);
    }

    private Integer calcMedinaForEvenQuantity(Integer first, Integer second) {
        return (first + second) / 2;
    }

    private List<Integer> findSequence(List<Integer> numbers, BiFunction<Integer, Integer, Boolean> sequenceType) {
        List<Integer> numbersSequence = new ArrayList<>();
        int resStart = 0;
        int resEnd = 0;
        int curStart = 0;
        int curEnd = 0;
        for (int i = 0; i < numbers.size() - 1; i++) {
            Integer currentNum = numbers.get(i);
            Integer nextNum = numbers.get(i + 1);
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
