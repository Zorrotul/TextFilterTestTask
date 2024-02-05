package cft.testTask.textFilter.util;

import java.util.concurrent.atomic.AtomicLong;

public class Counters {
    private AtomicLong amountOfElements = new AtomicLong(0);
    private AtomicLong maxLength = new AtomicLong(0);
    private AtomicLong minLength = new AtomicLong(Long.MAX_VALUE);
    private AtomicLong sum = new AtomicLong(0);

    public Counters() {

    }

    public void countNewElement(Long l) {
        amountOfElements.incrementAndGet();
        sum.addAndGet(l);
        maxLength.set(Math.max(maxLength.longValue(), l));
        minLength.set(Math.min(minLength.longValue(), l));
    }

    public void writeResults() {
        System.out.println(String.format("���������� ���������: %d /n " +
                "������������ ��������: %d /n" +
                "����������� ��������: %d /n" +
                "����� ���� �����: %d /n" +
                "������� ��������: " + (double) (sum.longValue() / amountOfElements.longValue()), amountOfElements, maxLength, minLength, sum));
    }
}


