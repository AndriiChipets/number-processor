package com.portaone.student_andrii_chipets.service.processor;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NumberProcessorImplTest {

    NumberProcessor processor = new NumberProcessorImpl();

    @Test
    void findMax() {
        int[] numbers1 = new int[]{1, 2, -30, 5};
        int[] numbers2 = new int[]{100};
        int[] numbers3 = new int[]{2, 2, 2};
        int[] numbers4 = new int[]{};

        Integer expected1 = 5;
        Integer expected2 = 100;
        Integer expected3 = 2;
        Integer expected4 = null;

        assertEquals(expected1, processor.findMax(numbers1));
        assertEquals(expected2, processor.findMax(numbers2));
        assertEquals(expected3, processor.findMax(numbers3));
        assertEquals(expected4, processor.findMax(numbers4));
    }

    @Test
    void findMin() {
        int[] numbers1 = new int[]{1, 2, -30, 5};
        int[] numbers2 = new int[]{100};
        int[] numbers3 = new int[]{2, 2, 2};
        int[] numbers4 = new int[]{};

        Integer expected1 = -30;
        Integer expected2 = 100;
        Integer expected3 = 2;
        Integer expected4 = null;

        assertEquals(expected1, processor.findMin(numbers1));
        assertEquals(expected2, processor.findMin(numbers2));
        assertEquals(expected3, processor.findMin(numbers3));
        assertEquals(expected4, processor.findMin(numbers4));
    }

    @Test
    void calcAvg() {
        int[] numbers1 = new int[]{1, 2, 3, 4};
        int[] numbers2 = new int[]{100};
        int[] numbers3 = new int[]{-202, -202, -202};
        int[] numbers4 = new int[]{};

        Double expected1 = 2.5d;
        Double expected2 = 100d;
        Double expected3 = -202d;
        Double expected4 = null;

        assertEquals(expected1, processor.calcAvg(numbers1));
        assertEquals(expected2, processor.calcAvg(numbers2));
        assertEquals(expected3, processor.calcAvg(numbers3));
        assertEquals(expected4, processor.calcAvg(numbers4));

    }

    @Test
    void findMedian() {
        int[] numbers1 = new int[]{1, 2, 3, 4};
        int[] numbers2 = new int[]{100};
        int[] numbers3 = new int[]{-202, -202, -202};
        int[] numbers4 = new int[]{};

        Integer expected1 = 2;
        Integer expected2 = 100;
        Integer expected3 = -202;
        Integer expected4 = null;

        assertEquals(expected1, processor.findMedian(numbers1));
        assertEquals(expected2, processor.findMedian(numbers2));
        assertEquals(expected3, processor.findMedian(numbers3));
        assertEquals(expected4, processor.findMedian(numbers4));
    }

    @Test
    void findLongestAscendingSequence() {
        int[] numbers1 = new int[]{1, 2, 3, 4};
        int[] numbers2 = new int[]{100};
        int[] numbers3 = new int[]{-202, -202, -202};
        int[] numbers4 = new int[]{};
        int[] numbers5 = new int[]{1, 2, 3, 4, 0, -1000, 100, 200, 300, 400, 500};

        List<Integer> expected1 = List.of(1, 2, 3, 4);
        List<Integer> expected2 = Collections.emptyList();
        List<Integer> expected3 = Collections.emptyList();
        List<Integer> expected4 = Collections.emptyList();
        List<Integer> expected5 = List.of(-1000, 100, 200, 300, 400, 500);

        assertEquals(expected1, processor.findLongestAscendingSequence(numbers1));
        assertEquals(expected2, processor.findLongestAscendingSequence(numbers2));
        assertEquals(expected3, processor.findLongestAscendingSequence(numbers3));
        assertEquals(expected4, processor.findLongestAscendingSequence(numbers4));
        assertEquals(expected5, processor.findLongestAscendingSequence(numbers5));
    }

    @Test
    void findLongestDescendingSequence() {
        int[] numbers1 = new int[]{4, 3, 2, 1};
        int[] numbers2 = new int[]{100};
        int[] numbers3 = new int[]{-202, -202, -202};
        int[] numbers4 = new int[]{};
        int[] numbers5 = new int[]{1, 2, 3, 4, 0, -1000, 100, 200, 300, 400, 500};

        List<Integer> expected1 = List.of(4, 3, 2, 1);
        List<Integer> expected2 = Collections.emptyList();
        List<Integer> expected3 = Collections.emptyList();
        List<Integer> expected4 = Collections.emptyList();
        List<Integer> expected5 = List.of(4, 0, -1000);

        assertEquals(expected1, processor.findLongestDescendingSequence(numbers1));
        assertEquals(expected2, processor.findLongestDescendingSequence(numbers2));
        assertEquals(expected3, processor.findLongestDescendingSequence(numbers3));
        assertEquals(expected4, processor.findLongestDescendingSequence(numbers4));
        assertEquals(expected5, processor.findLongestDescendingSequence(numbers5));
    }

    @Test
    void calcProcessTime() {
        long start = 100;
        long end = 300;

        Double expected = 0.2d;

        assertEquals(expected, processor.calcProcessTime(start, end));

    }

    @Test
    void getTotalNumQuantity() {
        int[] numbers1 = new int[]{1, 2, 3, 4};
        int[] numbers2 = new int[]{100};
        int[] numbers3 = new int[]{-202, -202, -202};
        int[] numbers4 = new int[]{};

        Integer expected1 = 4;
        Integer expected2 = 1;
        Integer expected3 = 3;
        Integer expected4 = 0;

        assertEquals(expected1, processor.getTotalNumQuantity(numbers1));
        assertEquals(expected2, processor.getTotalNumQuantity(numbers2));
        assertEquals(expected3, processor.getTotalNumQuantity(numbers3));
        assertEquals(expected4, processor.getTotalNumQuantity(numbers4));
    }
}
