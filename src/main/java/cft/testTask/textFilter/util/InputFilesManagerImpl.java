package cft.testTask.textFilter.util;

import cft.testTask.textFilter.config.Configuration;
import cft.testTask.textFilter.error.FileReaderException;
import cft.testTask.textFilter.handler.LineHandler;
import cft.testTask.textFilter.handler.LineHandlerImpl;

import java.io.*;
import java.util.List;

public class InputFilesManagerImpl implements InputFilesManager {

    private final LineHandler lineHandler;
    private final List<BufferedReader> fileReaders;

    public InputFilesManagerImpl(Configuration configuration) throws IOException {
        this.lineHandler = new LineHandlerImpl(configuration);
        this.fileReaders = initReaders(configuration);
    }

    @Override
    public void filterFiles() {
        for (BufferedReader br : fileReaders) {
            br.lines().forEach(lineHandler);
        }
        lineHandler.endHandle();
    }

    @Override
    public void close() throws Exception {
        for (BufferedReader fileReader : fileReaders) {
            fileReader.close();
        }
    }

    private List<BufferedReader> initReaders(Configuration configuration) {

        return configuration.getInputFileNames().stream()
                .map(File::new)
                .peek(file -> {
                    if (!file.exists()) {
                        System.out.printf("Файл: %s не найден" + System.lineSeparator(), file);
                    }
                })
                .filter(File::exists)
                .map(this::produceFileReaderSneaky)
                .map(BufferedReader::new)
                .toList();
    }

    private FileReader produceFileReaderSneaky(File file) {
        try {
            return new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new FileReaderException(String.format("Ошибка при чтении файла: %s", file), e);
        }
    }

}