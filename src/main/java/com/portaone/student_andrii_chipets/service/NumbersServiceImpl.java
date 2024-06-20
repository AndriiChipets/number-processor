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
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

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
                .withLongestAscNumSequence(List.of(0))
                .withLongestDescNumSequence(List.of(0))
                .build();
        long end = System.currentTimeMillis();
        numbersStatistic.setTotalProcessingTime(calcProcessTime(start, end));
        return NumbersStatisticMapper.mapToNumStatDto(numbersStatistic);
    }

    public List<Integer> getNumbersFromFile(MultipartFile file) {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            return bufferedReader.lines()
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public int findMax(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).max().getAsInt();
    }

    public int findMin(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).min().getAsInt();
    }

    public double calcAvg(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).average().getAsDouble();
    }

    public int findMedian(List<Integer> numbers) {
        int quantity = numbers.size();
        return quantity % 2 == 0 ?
                calcMedinaForEvenQuantity(numbers.get(quantity / 2), numbers.get(quantity / 2 - 1))
                : numbers.get(quantity / 2);
    }

    public List<Integer> findLongestAscendingSequence(int[] numbers) {
        BiFunction<Integer, Integer, Boolean> isNextLargerThanCurrent = (a, b) -> b > a;
        return findSequence(numbers, isNextLargerThanCurrent);
    }

    public List<Integer> findLongestDescendingSequence(int[] numbers) {
        BiFunction<Integer, Integer, Boolean> isNextSmallerThanCurrent = (a, b) -> b < a;
        return findSequence(numbers, isNextSmallerThanCurrent);
    }

    private Integer calcMedinaForEvenQuantity(Integer first, Integer second) {
        return (first + second) / 2;
    }

    private List<Integer> findSequence(int[] numbers, BiFunction<Integer, Integer, Boolean> sequenceType) {
        List<Integer> numbersSequence = new ArrayList<>();
        int resStart = 0;
        int resEnd = 0;
        int curStart = 0;
        int curEnd = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            Integer currentNum = numbers[i];
            Integer nextNum = numbers[i + 1];
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
            numbersSequence.add(numbers[j]);
        }
        return numbersSequence;
    }

    public double calcProcessTime(long start, long end) {
        return (double) (end - start) / 1000;
    }
}
