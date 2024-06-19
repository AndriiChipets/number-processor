package com.portaone.student_andrii_chipets.domain;

import lombok.*;

import java.util.List;

@Builder(setterPrefix = "with")
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class NumbersStatistic {
    private List<Integer> numbersFromFile;
    private int min;
    private int max;
    private int median;
    private double average;
    private List<Integer> longestAscNumSequence;
    private List<Integer> longestDescNumSequence;
    @Setter
    private Double totalProcessingTime;
}
