package cft.testTask.textFilter.handler;

public class Test {

    public Object test(String line) {
        Object result = null;
        try {
            result = Long.valueOf(line);
        } catch (NumberFormatException nfe) {
            //�� ����� �����, ��, ��� ������������
            try {
                result = Double.valueOf(line);
            } catch (NumberFormatException nfe2) {
                // ������, ��� ������
                result = line;
            }
        }
        //System.out.printf("result=[%s], clazz=[%s]%n", result, result.getClass());
        //System.out.println(result instanceof Integer);

        return result;
    }

}