package com.portaone.student_andrii_chipets.domain;

import lombok.*;

import java.util.List;

@Builder(setterPrefix = "with")
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class NumbersStatistic {
    private Integer min;
    private Integer max;
    private Integer median;
    private Double average;
    private List<Integer> longestAscNumSequence;
    private List<Integer> longestDescNumSequence;
    @Setter
    private Double totalProcessingTime;
    private Integer totalNumQuantity;
}
