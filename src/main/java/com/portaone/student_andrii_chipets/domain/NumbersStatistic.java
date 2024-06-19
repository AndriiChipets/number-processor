package com.portaone.student_andrii_chipets.domain;

import lombok.*;

import java.util.List;

@Builder(setterPrefix = "with")
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class NumbersStatistic {
    private List<Long> numbersFromFile;
    private long min;
    private long max;
    private long median;
    private double average;
    private List<Long> longestAscNumSequence;
    private List<Long> longestDescNumSequence;
    @Setter
    private Double totalProcessingTime;
}
