package cft.testTask.textFilter.counters;

public class DoubleCounters implements Counters {
    private Long amountOfDoubleElements = 0l;
    private Double maxDoubleValue = 0.0;
    private Double minDoubleValue = Double.MAX_VALUE;
    private Double sumDouble = 0.0;

    public void incCounter(Object line) {
        amountOfDoubleElements++;
        sumDouble = sumDouble + (double) line;
        maxDoubleValue = Math.max(maxDoubleValue, (double) line);
        minDoubleValue = Math.min(minDoubleValue, (double) line);
    }

    public void printResult() {
        if (amountOfDoubleElements > 0) {
            System.out.println(String.format("Количество вещественных чисел: %d\n" +
                    "Максимальное значение вещественных чисел: %f\n" +
                    "Минимальоне значение вещественных чисел: %f\n" +
                    "Сумма всех вещественных чисел: %f\n" +
                    "Среднее значение вещественных чисел: " + (sumDouble / amountOfDoubleElements), amountOfDoubleElements, maxDoubleValue, minDoubleValue, sumDouble));
        }
    }
}


