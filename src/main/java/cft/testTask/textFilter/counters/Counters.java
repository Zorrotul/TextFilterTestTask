package cft.testTask.textFilter.counters;

import cft.testTask.textFilter.config.StatisticMode;

public interface Counters {
    /**
     *Добавляет объект для подчсётов
     */
    void incCounter(Object object);

    /**
     *Выводит в консоль результаты подсчётов
     */
    void printResult();
}