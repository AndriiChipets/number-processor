package com.portaone.student_andrii_chipets.service.processor;

import java.util.List;

public interface NumberProcessor {

    Integer findMax(int[] numbers);

    Integer findMin(int[] numbers);

    Double calcAvg(int[] numbers);

    Integer findMedian(int[] numbers);

    List<Integer> findLongestAscendingSequence(int[] numbers);

    List<Integer> findLongestDescendingSequence(int[] numbers);

    Double calcProcessTime(long start, long end);

    Integer getTotalNumQuantity(int[] numbers);

}
