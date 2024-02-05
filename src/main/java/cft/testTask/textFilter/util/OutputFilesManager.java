package cft.testTask.textFilter.util;

import cft.testTask.textFilter.config.Configuration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class OutputFilesManager {
    private final Map<Path, BufferedWriter> bufferedWriterHashMap = new HashMap<>();
    private final ArrayList<StandardOpenOption> NEW_FILE_WRITE_OPTIONS = new ArrayList<>(Arrays.asList(StandardOpenOption.CREATE));

    public OutputFilesManager (boolean addToExistFiles){
        if (addToExistFiles){
            NEW_FILE_WRITE_OPTIONS.add(StandardOpenOption.APPEND);
        }
    }

    //private final Configuration configuration;

    public BufferedWriter createFileAndGetWriter(Path path) throws IOException {
        StandardOpenOption[] NEW_FILE_WRITE_OPTIONS_ARRAY = NEW_FILE_WRITE_OPTIONS.toArray(new StandardOpenOption[NEW_FILE_WRITE_OPTIONS.size()]);

        BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8, NEW_FILE_WRITE_OPTIONS_ARRAY);
        return bw;
    }

    public void writeLineIntoFile(Path path, String line) throws IOException{
        BufferedWriter bw = bufferedWriterHashMap.computeIfAbsent(path,s -> {
            try {
                return createFileAndGetWriter(s);
            } catch (IOException e) {
                throw new RuntimeException(e);//TODO: оработать ошибку нормально
            }
        });
        bw.write(line);
    }

}
