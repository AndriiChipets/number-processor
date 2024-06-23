package com.portaone.student_andrii_chipets.service.processor;

import java.util.List;

public interface NumberProcessor {

    int findMax(int[] numbers);

    int findMin(int[] numbers);

    double calcAvg(int[] numbers);

    int findMedian(int[] numbers);

    List<Integer> findLongestAscendingSequence(int[] numbers);

    List<Integer> findLongestDescendingSequence(int[] numbers);

    double calcProcessTime(long start, long end);

    int getTotalNumQuantity(int[] numbers);

}
