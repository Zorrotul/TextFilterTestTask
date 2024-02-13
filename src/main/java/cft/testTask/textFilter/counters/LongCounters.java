package cft.testTask.textFilter.counters;

import cft.testTask.textFilter.config.StatisticMode;

public class LongCounters implements Counters {

    private Long amountOfLongElements = 0L;
    private Long maxLongValue = 0L;
    private Long minLongValue = Long.MAX_VALUE;
    private Long sumLong = 0L;
    private final StatisticMode statisticMode;

    public LongCounters(StatisticMode statisticMode) {
        this.statisticMode = statisticMode;
    }

    public void incCounter(Object line) {
        amountOfLongElements++;
        sumLong = sumLong + (long) line;
        maxLongValue = Math.max(maxLongValue, (long) line);
        minLongValue = Math.min(minLongValue, (long) line);
    }

    public void printResult() {
        if (amountOfLongElements > 0) {
            System.out.printf("���������� ����� �����: %d", amountOfLongElements);
            if (statisticMode.equals(StatisticMode.FULL)) {
                System.out.printf(
                        System.lineSeparator() + "������������ �������� ����� �����: %d" + System.lineSeparator() +
                                "����������� �������� ����� �����: %d" + System.lineSeparator() +
                                "����� ���� ����� �����: %d" + System.lineSeparator() +
                                "������� �������� ����� �����: " + ((double) sumLong / (double) amountOfLongElements)
                        , maxLongValue, minLongValue, sumLong);
            }
        }
    }
}