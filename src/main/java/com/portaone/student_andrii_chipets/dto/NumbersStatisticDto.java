package com.portaone.student_andrii_chipets.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder(setterPrefix = "with")
@Data
@AllArgsConstructor
public class NumbersStatisticDto {
    private String min;
    private String max;
    private String median;
    private String average;
    private String longestAscNumSequence;
    private String longestDescNumSequence;
    private String totalProcessingTime;
    private String totalNumQuantity;
}
