package cft.testTask.sortIt;

import cft.testTask.sortIt.config.Configuration;
import cft.testTask.sortIt.handle.Handler;

import java.io.IOException;

public class SortItApplication {

    public static void main(String[] args) throws IOException {

        System.out.println("jar started");
        Configuration configuration = new Configuration(args);

        Handler handler = new Handler(configuration);
        handler.sort();

    }

}
