package cft.testTask.textFilter.counters;

import java.util.concurrent.atomic.AtomicLong;

public class LongCounters implements Counters {
    private Long amountOfLongElements = 0L;
    private Long maxLongValue = 0L;
    private Long minLongValue = Long.MAX_VALUE;
    private Long sumLong = 0L;

    public void incCounter(Object line) {
        amountOfLongElements++;
        sumLong = sumLong + (long) line;
        maxLongValue = Math.max(maxLongValue.longValue(), (long) line);
        minLongValue = Math.min(minLongValue.longValue(), (long) line);
    }

    public void printResult() {
        if (amountOfLongElements.longValue() > 0) {
            System.out.println(String.format("���������� ����� �����: %d \n" +
                    "������������ �������� ����� �����: %d \n" +
                    "����������� �������� ����� �����: %d \n" +
                    "����� ���� ����� �����: %d \n" +
                    "������� �������� ����� �����: " + ((double)sumLong.longValue() / (double)amountOfLongElements.longValue()), amountOfLongElements, maxLongValue, minLongValue, sumLong));
        }
    }
}


