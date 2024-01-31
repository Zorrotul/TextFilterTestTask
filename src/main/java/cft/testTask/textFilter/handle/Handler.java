package cft.testTask.textFilter.handle;

import cft.testTask.textFilter.config.Configuration;
import cft.testTask.textFilter.util.ReadersInitializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class Handler {
    private final Configuration configuration;
    private final List<BufferedReader> bufferedReaders;


    public Handler(Configuration configuration) throws IOException {
        this.configuration = configuration;
        this.bufferedReaders = ReadersInitializer.getReaders(configuration);
    }


    public String getStringFromFile(BufferedReader br) throws IOException {
        return br.readLine();
    }

    public int getNumberFromFile(BufferedReader br) throws IOException {
        return Integer.parseInt(br.readLine());
    }
}



