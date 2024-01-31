package cft.testTask.textFilter;

import cft.testTask.textFilter.config.Configuration;
import cft.testTask.textFilter.handle.Handler;

import java.io.IOException;

public class TextFilterApplication {

    public static void main(String[] args) throws IOException {

        System.out.println("jar started");
        Configuration configuration = new Configuration(args);

        Handler handler = new Handler(configuration);


    }

}
