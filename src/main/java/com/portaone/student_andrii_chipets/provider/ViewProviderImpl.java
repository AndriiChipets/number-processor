package com.portaone.student_andrii_chipets.provider;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViewProviderImpl implements ViewProvider {

    private final static String NO_MAX_NUM_PRESENT = "There is no max number present";
    private final static String NO_MIN_NUM_PRESENT = "There is no min number present";
    private final static String NO_AVG_NUM_PRESENT = "There is no avg number present";
    private final static String NO_ASC_SEQUENCE_PRESENT = "There is no ascending sequence present";
    private final static String NO_DSC_SEQUENCE_PRESENT = "There is no descending sequence present";

    @Override
    public String createViewMaxNum(Integer num) {
        return createViewNum(num, NO_MAX_NUM_PRESENT);
    }

    @Override
    public String createViewMinNum(Integer num) {
        return createViewNum(num, NO_MIN_NUM_PRESENT);
    }

    @Override
    public String createViewAvgNum(Double num) {
        return createViewNum(num, NO_AVG_NUM_PRESENT);
    }

    @Override
    public String createViewLongestAscNumSequence(List<Integer> sequence) {
        return createViewNumSequence(sequence, NO_ASC_SEQUENCE_PRESENT);
    }

    @Override
    public String createViewLongestDescNumSequence(List<Integer> sequence) {
        return createViewNumSequence(sequence, NO_DSC_SEQUENCE_PRESENT);
    }

    @Override
    public String createViewTotalProcessingTime(Double num) {
        return String.format("%.4f", num);
    }

    @Override
    public String createViewIntNum(Integer num) {
        return String.valueOf(num);
    }

    private String createViewNumSequence(List<Integer> sequence, String message) {
        if (sequence == null || sequence.isEmpty()) {
            return message;
        }
        return sequence
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    private String createViewNum(Integer num, String message) {
        if (num == null) {
            return message;
        }
        return String.valueOf(num);
    }

    private String createViewNum(Double num, String message) {
        if (num == null) {
            return message;
        }
        return String.format("%.2f", num);
    }
}
