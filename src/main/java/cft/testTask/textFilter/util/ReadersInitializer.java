package cft.testTask.textFilter.util;

import cft.testTask.textFilter.config.Configuration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadersInitializer {

    public static List<BufferedReader> getReaders(Configuration configuration) throws IOException {
        var res = configuration.getInputFileNames().stream()
                .peek(System.out::println)
                .map(File::new)
                .map(ReadersInitializer::produceFileReaderSneaky)
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