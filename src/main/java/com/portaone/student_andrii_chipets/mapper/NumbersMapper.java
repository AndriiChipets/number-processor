package com.portaone.student_andrii_chipets.mapper;

import com.portaone.student_andrii_chipets.domain.NumbersStatistic;
import com.portaone.student_andrii_chipets.dto.NumbersStatisticDto;
import com.portaone.student_andrii_chipets.provider.ViewProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NumbersMapper {

    private final ViewProvider viewProvider;

    public NumbersStatisticDto mapToNumStatDto(NumbersStatistic ns) {

        return NumbersStatisticDto.builder()
                .withMin(viewProvider.createViewMaxNum(ns.getMin()))
                .withMax(viewProvider.createViewMinNum(ns.getMax()))
                .withMedian(viewProvider.createViewIntNum(ns.getMedian()))
                .withAverage(viewProvider.createViewAvgNum(ns.getAverage()))
                .withLongestAscNumSequence(viewProvider.createViewLongestAscNumSequence(ns.getLongestAscNumSequence()))
                .withLongestDescNumSequence(viewProvider.createViewLongestDescNumSequence(ns.getLongestDescNumSequence()))
                .withTotalProcessingTime(viewProvider.createViewTotalProcessingTime(ns.getTotalProcessingTime()))
                .withTotalNumQuantity(viewProvider.createViewIntNum(ns.getTotalNumQuantity()))
                .build();
    }
}
