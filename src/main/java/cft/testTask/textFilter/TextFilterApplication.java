package cft.testTask.textFilter;

import cft.testTask.textFilter.config.Configuration;
import cft.testTask.textFilter.handler.Test;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

public class TextFilterApplication {

    public static void main(String[] args) throws IOException {

        System.out.println("jar started");
        String[] testArgs = new String[]{"-s", "-a", "-o", "/some/path", "-p", "sample-", "in1.txt", "in2.txt"};
        Configuration configuration = new Configuration(testArgs);

        configuration.printConfig();

        //Handler handler = new Handler(configuration);
        Test test = new Test();
        test.test("-0.001");
        test.test("1234567890123456789");
        test.test("PIIIIDR");
        AtomicLong minLength= new AtomicLong();
        System.out.println(minLength);


    }
}

