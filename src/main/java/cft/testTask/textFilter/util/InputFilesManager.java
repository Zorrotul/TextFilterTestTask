package cft.testTask.textFilter.util;

import cft.testTask.textFilter.config.Configuration;
import cft.testTask.textFilter.handler.Handler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InputFilesManager {


    public static List<BufferedReader> getReaders(Configuration configuration) throws IOException {
        var res = configuration.getInputFileNames().stream()
                .peek(System.out::println)
                .map(File::new)
                .map(InputFilesManager::produceFileReaderSneaky)
                .map(BufferedReader::new)
                .toList();
        return new ArrayList<>(res);
    }

    private static FileReader produceFileReaderSneaky(File file) {
        try {
            return new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void filterFiles(Configuration configuration) throws IOException {
        List<BufferedReader> bufferedReaders = InputFilesManager.getReaders(configuration);
        String line;
        Handler handler = new Handler(configuration);
        for (BufferedReader br : bufferedReaders) {
            while ((line = br.readLine()) != null) {
                handler.handleLine(configuration, line);

            }
        }
    }
}


//        List<BufferedReader> bufferedReaders =new ArrayList<>();
//
//        for (File inputFile : inputFiles) {
//            bufferedReaders.add(new BufferedReader(new FileReader(inputFile)));
//        }
//
//        List<BufferedReader> bufferedReaders2 = inputFiles.stream()
//                .map(inputFile-> new FileReader(inputFile))
//                .map(fileReader -> new BufferedReader(fileReader))
//                .collect(Collectors.toMap());