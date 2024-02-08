package cft.testTask.textFilter.counters;

public class StringCounters implements Counters {
    private Long amountOfStringElements = 0l;
    private int maxStringLength;
    private int minStringLength = 37; //длинна самого длинного слова. Похорошему нужно было бы написать Integer.MAX_VALUE


    public void incCounter(Object line) {
        amountOfStringElements++;
        maxStringLength = Math.max(maxStringLength, line.toString().length());
        minStringLength = Math.min(minStringLength, line.toString().length());
    }

    public void printResult() {
        if (amountOfStringElements > 0) {
            System.out.println(String.format("Количество символьных элементов: %d \n" +
                    "Максимальная длина слова: %d \n" +
                    "Минимальная длина слова: %d", amountOfStringElements, maxStringLength, minStringLength));
        }
    }
}


