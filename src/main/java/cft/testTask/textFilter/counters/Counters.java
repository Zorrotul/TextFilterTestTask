package cft.testTask.textFilter.counters;

import cft.testTask.textFilter.config.StatisticMode;

public interface Counters {
    /**
     *��������� ������ ��� ��������
     */
    void incCounter(Object object);

    /**
     *������� � ������� ���������� ���������
     */
    void printResult();
}