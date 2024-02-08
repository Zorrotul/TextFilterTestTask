package cft.testTask.textFilter.util;

import cft.testTask.textFilter.config.Configuration;
import cft.testTask.textFilter.exceptions.FileRWException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class OutputFilesManager{
    private final Map<Path, BufferedWriter> bufferedWriterHashMap = new HashMap<>();
    private final ArrayList<StandardOpenOption> NEW_FILE_WRITE_OPTIONS = new ArrayList<>(Arrays.asList(StandardOpenOption.CREATE,StandardOpenOption.APPEND, StandardOpenOption.WRITE));

    public OutputFilesManager (boolean addToExistFiles){
        if (addToExistFiles){
            NEW_FILE_WRITE_OPTIONS.add(StandardOpenOption.TRUNCATE_EXISTING);
            NEW_FILE_WRITE_OPTIONS.remove(StandardOpenOption.APPEND);
        }
    }

    public BufferedWriter createFileAndGetWriter(Path path) throws IOException {
        StandardOpenOption[] NEW_FILE_WRITE_OPTIONS_ARRAY = NEW_FILE_WRITE_OPTIONS.toArray(new StandardOpenOption[NEW_FILE_WRITE_OPTIONS.size()]);
        return Files.newBufferedWriter(path, StandardCharsets.UTF_8, NEW_FILE_WRITE_OPTIONS_ARRAY);
    }

    public void writeLineIntoFile(Path path, Object line) {
        BufferedWriter bw = bufferedWriterHashMap.computeIfAbsent(path,s -> {
            try {
                Path pathToFile = Paths.get(s.toString());
                System.out.println(pathToFile.toAbsolutePath());
                return createFileAndGetWriter(s);
            } catch (IOException e) {
                throw new  FileRWException("Проблема в создании файла", e);
            }
        });
        try {
            bw.write(line.toString()+ "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeWriters() throws IOException{
        for (Map.Entry<Path,BufferedWriter> entry: bufferedWriterHashMap.entrySet()){
            entry.getValue().close();
        }
    }
}
