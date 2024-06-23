package com.portaone.student_andrii_chipets.service.processor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BiFunction;

@Service
@Log4j2
public class NumberProcessorImpl implements NumberProcessor {

    @Override
    public Integer findMax(int[] numbers) {
        Integer max = null;
        OptionalInt maxOptional = Arrays.stream(numbers).max();
        if (maxOptional.isPresent()) {
            max = maxOptional.getAsInt();
        }
        log.info("max number: " + "{}", max);
        return max;
    }

    @Override
    public Integer findMin(int[] numbers) {
        Integer min = null;
        OptionalInt minOptional = Arrays.stream(numbers).min();
        if (minOptional.isPresent()) {
            min = minOptional.getAsInt();
        }
        log.info("min number: " + "{}", min);
        return min;
    }

    @Override
    public Double calcAvg(int[] numbers) {
        Double avg = null;
        OptionalDouble avgOptional = Arrays.stream(numbers).average();
        if (avgOptional.isPresent()) {
            avg = avgOptional.getAsDouble();
        }
        log.info("avg number: " + "{}", avg);
        return avg;
    }

    @Override
    public Integer findMedian(int[] numbers) {
        int quantity = numbers.length;
        Integer median = null;
        if (quantity != 0) {
            median = quantity % 2 == 0 ?
                    calcMedinaForEvenQuantity(numbers[quantity / 2], numbers[quantity / 2 - 1])
                    : numbers[quantity / 2];
        }
        log.info("median number: " + "{}", median);
        return median;
    }

    @Override
    public List<Integer> findLongestAscendingSequence(int[] numbers) {
        BiFunction<Integer, Integer, Boolean> isNextLargerThanCurrent = (a, b) -> b > a;
        List<Integer> ascSequence = findSequence(numbers, isNextLargerThanCurrent);
        log.info("ascending sequence: " + "{}", ascSequence);
        return ascSequence;
    }

    @Override
    public List<Integer> findLongestDescendingSequence(int[] numbers) {
        BiFunction<Integer, Integer, Boolean> isNextSmallerThanCurrent = (a, b) -> b < a;
        List<Integer> descSequence = findSequence(numbers, isNextSmallerThanCurrent);
        log.info("descending sequence: " + "{}", descSequence);
        return descSequence;
    }

    @Override
    public Double calcProcessTime(long start, long end) {
        Double processTime = (double) (end - start) / 1000;
        log.info("process time: " + "{}", processTime);
        return processTime;
    }

    @Override
    public Integer getTotalNumQuantity(int[] numbers) {
        Integer totalNumQuantity = numbers.length;
        log.info("total num quantity: " + "{}", totalNumQuantity);
        return totalNumQuantity;
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
                if (isLongestSequence(resEnd, resStart, curEnd, curStart)) {
                    resStart = curStart;
                    resEnd = curEnd;
                }
                curStart = i + 1;
                curEnd = curStart;
            }
        }
        if (isLongestSequence(resEnd, resStart, curEnd, curStart)) {
            resStart = curStart;
            resEnd = curEnd;
        }
        if (resEnd - resStart > 1) {
            for (int j = resStart; j <= resEnd; j++) {
                numbersSequence.add(numbers[j]);
            }
        }
        return numbersSequence;
    }

    private boolean isLongestSequence(int resEnd, int resStart, int curEnd, int curStart) {
        return (resEnd - resStart) < (curEnd - curStart);
    }
}
