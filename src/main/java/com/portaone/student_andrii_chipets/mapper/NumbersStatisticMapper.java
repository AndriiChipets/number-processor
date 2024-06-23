package com.portaone.student_andrii_chipets.mapper;

import com.portaone.student_andrii_chipets.domain.NumbersStatistic;
import com.portaone.student_andrii_chipets.dto.NumbersStatisticDto;

public class NumbersStatisticMapper {

    public static NumbersStatisticDto mapToNumStatDto(NumbersStatistic ns) {

        return NumbersStatisticDto.builder()
                .withMin(String.valueOf(ns.getMin()))
                .withMax(String.valueOf(ns.getMax()))
                .withMedian(String.valueOf(ns.getMedian()))
                .withAverage(String.format("%.2f", ns.getAverage()))
                .withLongestAscNumSequence(String.valueOf(ns.getLongestAscNumSequence()))
                .withLongestDescNumSequence(String.valueOf(ns.getLongestDescNumSequence()))
                .withTotalProcessingTime(String.format("%.4f", ns.getTotalProcessingTime()))
                .withTotalNumQuantity(String.valueOf(ns.getTotalNumQuantity()))
                .build();
    }
}
