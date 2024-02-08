package cft.testTask.textFilter.counters;

public class StringCounters implements Counters {
    private Long amountOfStringElements = 0l;
    private int maxStringLength;
    private int minStringLength = 37; //������ ������ �������� �����. ���������� ����� ���� �� �������� Integer.MAX_VALUE


    public void incCounter(Object line) {
        amountOfStringElements++;
        maxStringLength = Math.max(maxStringLength, line.toString().length());
        minStringLength = Math.min(minStringLength, line.toString().length());
    }

    public void printResult() {
        if (amountOfStringElements > 0) {
            System.out.println(String.format("���������� ���������� ���������: %d \n" +
                    "������������ ����� �����: %d \n" +
                    "����������� ����� �����: %d", amountOfStringElements, maxStringLength, minStringLength));
        }
    }
}


