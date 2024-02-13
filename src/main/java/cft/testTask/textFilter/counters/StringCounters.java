package cft.testTask.textFilter.counters;

import cft.testTask.textFilter.config.StatisticMode;

public class StringCounters implements Counters {

    private Long amountOfStringElements = 0L;
    private int maxStringLength;
    private int minStringLength = 37; //������ ������ �������� �����. ���������� ����� ���� �� �������� Integer.MAX_VALUE
    private final StatisticMode statisticMode;

    public StringCounters(StatisticMode statisticMode) {
        this.statisticMode = statisticMode;
    }

    public void incCounter(Object line) {
        amountOfStringElements++;
        maxStringLength = Math.max(maxStringLength, line.toString().length());
        minStringLength = Math.min(minStringLength, line.toString().length());
    }

    public void printResult() {
        if (amountOfStringElements > 0) {
            System.out.printf("���������� ���������� ���������: %d", amountOfStringElements);
            if (statisticMode.equals(StatisticMode.FULL)) {
                System.out.printf(
                        System.lineSeparator() + "������������ ����� �����: %d" + System.lineSeparator() +
                                "����������� ����� �����: %d", maxStringLength, minStringLength);
            }
        }
    }
}