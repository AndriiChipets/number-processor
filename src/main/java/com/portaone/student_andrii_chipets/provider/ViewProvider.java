package com.portaone.student_andrii_chipets.provider;

import java.util.List;

public interface ViewProvider {

    String createViewMaxNum(Integer num);

    String createViewMinNum(Integer num);

    String createViewAvgNum(Double num);

    String createViewLongestAscNumSequence(List<Integer> sequence);

    String createViewLongestDescNumSequence(List<Integer> sequence);

    String createViewTotalProcessingTime(Double num);

    String createViewIntNum(Integer num);

}
