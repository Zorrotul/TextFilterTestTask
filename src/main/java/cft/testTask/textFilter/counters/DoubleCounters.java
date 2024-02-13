package cft.testTask.textFilter.counters;

import cft.testTask.textFilter.config.StatisticMode;

public class DoubleCounters implements Counters {

    private final StatisticMode statisticMode;
    private Long amountOfDoubleElements = 0L;
    private Double maxDoubleValue = 0.0;
    private Double minDoubleValue = Double.MAX_VALUE;
    private Double sumDouble = 0.0;

    public DoubleCounters(StatisticMode statisticMode) {
        this.statisticMode = statisticMode;
    }

    public void incCounter(Object line) {
        amountOfDoubleElements++;
        sumDouble = sumDouble + (double) line;
        maxDoubleValue = Math.max(maxDoubleValue, (double) line);
        minDoubleValue = Math.min(minDoubleValue, (double) line);
    }

    public void printResult() {
        if (amountOfDoubleElements > 0) {
            System.out.printf("Количество вещественных чисел: %d", amountOfDoubleElements);
            if (statisticMode.equals(StatisticMode.FULL)) {
                System.out.printf(
                        System.lineSeparator() + "Максимальное значение вещественных чисел: %f" + System.lineSeparator() +
                                "Минимальоне значение вещественных чисел: %f" + System.lineSeparator() +
                                "Сумма всех вещественных чисел: %f" + System.lineSeparator() +
                                "Среднее значение вещественных чисел: " + (sumDouble / amountOfDoubleElements) + System.lineSeparator(), maxDoubleValue, minDoubleValue, sumDouble);
            }
        }
    }
}