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
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

@Service
public class NumbersServiceImpl implements NumbersService {

    @Override
    public NumbersStatisticDto processNumbers(MultipartFile file) {
        long start = System.currentTimeMillis();
        int[] numbers = getNumbersFromFile(file);
        NumbersStatistic numbersStatistic = NumbersStatistic.builder()
                .withMin(findMin(numbers))
                .withMax(findMax(numbers))
                .withMedian(findMedian(numbers))
                .withAverage(calcAvg(numbers))
                .withLongestAscNumSequence(findLongestAscendingSequence(numbers))
                .withLongestDescNumSequence(findLongestDescendingSequence(numbers))
                .withTotalNumQuantity(getTotalNumQuantity(numbers))
                .build();
        long end = System.currentTimeMillis();
        numbersStatistic.setTotalProcessingTime(calcProcessTime(start, end));
        return NumbersStatisticMapper.mapToNumStatDto(numbersStatistic);
    }

    public int[] getNumbersFromFile(MultipartFile file) {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            return bufferedReader.lines()
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .toArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new int[0];
    }

    public int findMax(int[] numbers) {
        return Arrays.stream(numbers).max().getAsInt();
    }

    public int findMin(int[] numbers) {
        return Arrays.stream(numbers).min().getAsInt();
    }

    public double calcAvg(int[] numbers) {
        return Arrays.stream(numbers).average().getAsDouble();
    }

    public int findMedian(int[] numbers) {
        int quantity = numbers.length;
        return quantity % 2 == 0 ?
                calcMedinaForEvenQuantity(numbers[quantity / 2], numbers[quantity / 2 - 1])
                : numbers[quantity / 2];
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

    private int getTotalNumQuantity(int[] numbers) {
        return numbers.length;
    }
}
