package com.portaone.student_andrii_chipets.service.processor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

@Service
public class NumberProcessorImpl implements NumberProcessor {

    @Override
    public int findMax(int[] numbers) {
        return Arrays.stream(numbers).max().getAsInt();
    }

    @Override
    public int findMin(int[] numbers) {
        return Arrays.stream(numbers).min().getAsInt();
    }

    @Override
    public double calcAvg(int[] numbers) {
        return Arrays.stream(numbers).average().getAsDouble();
    }

    @Override
    public int findMedian(int[] numbers) {
        int quantity = numbers.length;
        return quantity % 2 == 0 ?
                calcMedinaForEvenQuantity(numbers[quantity / 2], numbers[quantity / 2 - 1])
                : numbers[quantity / 2];
    }

    @Override
    public List<Integer> findLongestAscendingSequence(int[] numbers) {
        BiFunction<Integer, Integer, Boolean> isNextLargerThanCurrent = (a, b) -> b > a;
        return findSequence(numbers, isNextLargerThanCurrent);
    }

    @Override
    public List<Integer> findLongestDescendingSequence(int[] numbers) {
        BiFunction<Integer, Integer, Boolean> isNextSmallerThanCurrent = (a, b) -> b < a;
        return findSequence(numbers, isNextSmallerThanCurrent);
    }

    @Override
    public double calcProcessTime(long start, long end) {
        return (double) (end - start) / 1000;
    }

    @Override
    public int getTotalNumQuantity(int[] numbers) {
        return numbers.length;
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
}
