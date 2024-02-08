package cft.testTask.textFilter.util;

import cft.testTask.textFilter.config.Configuration;
import cft.testTask.textFilter.handler.LineHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InputFilesManager implements AutoCloseable {
    private final LineHandler lineHandler;
    private final List<BufferedReader> fileReaders;

    public InputFilesManager(Configuration configuration) throws IOException {
        this.lineHandler = new LineHandler(configuration);
        this.fileReaders = initReaders(configuration);

    }

    private List<BufferedReader> initReaders(Configuration configuration) throws IOException {
        var res = configuration.getInputFileNames().stream()
                .peek(System.out::println)
                .map(File::new)
                .map(this::produceFileReaderSneaky)
                .map(BufferedReader::new)
                .toList();
        return new ArrayList<>(res);
    }

    private FileReader produceFileReaderSneaky(File file) {
        try {
            return new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void filterFiles() throws IOException {
        for (BufferedReader br : fileReaders) {
            br.lines().forEach(lineHandler);
//            while ((line = br.readLine()) != null) {
//                lineHandler.accept(line);
//            }
        }
        lineHandler.endHandle();
    }

    @Override
    public void close() throws Exception {
        for (BufferedReader fileReader : fileReaders) {
            fileReader.close();
        }
    }
}


