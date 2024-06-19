package com.portaone.student_andrii_chipets.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class NumbersStatistic {
    private final List<Long> numbersFromFile;
    private long min;
    private long max;
    private long median;
    private long average;
    private List<Long> longestAscNumSequence;
    private List<Long> longestDescNumSequence;
    private Double totalProcessingTime;
}
