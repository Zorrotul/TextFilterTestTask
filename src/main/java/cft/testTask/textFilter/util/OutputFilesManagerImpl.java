package cft.testTask.textFilter.util;

import cft.testTask.textFilter.error.FileWiterException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OutputFilesManagerImpl implements OutputFilesManager {

    private final Map<Path, BufferedWriter> bufferedWriterHashMap = new HashMap<>();
    private final ArrayList<StandardOpenOption> newFileWriteOptions;

    public OutputFilesManagerImpl(boolean addToExistFiles) {
        this.newFileWriteOptions = new ArrayList<>();
        newFileWriteOptions.add(StandardOpenOption.CREATE);
        newFileWriteOptions.add(StandardOpenOption.WRITE);

        if (addToExistFiles) {
            newFileWriteOptions.add(StandardOpenOption.TRUNCATE_EXISTING);
        } else {
            newFileWriteOptions.add(StandardOpenOption.APPEND);
        }
    }

    @Override
    public void writeLineIntoFile(Path path, Object line) {
        BufferedWriter bw = bufferedWriterHashMap.computeIfAbsent(path, s -> {
            try {
                return createFileAndGetWriter(s);
            } catch (IOException e) {
                throw new FileWiterException(String.format("Can`t create file of this path: %s", path), e);
            }
        });
        try {
            bw.write(line.toString() + System.lineSeparator());
        } catch (IOException e) {
            throw new FileWiterException(String.format("Can`t write this line %s", line), e);
        }
    }

    private BufferedWriter createFileAndGetWriter(Path path) throws IOException {
        StandardOpenOption[] newFileWriteOptionsArray = newFileWriteOptions.toArray(new StandardOpenOption[newFileWriteOptions.size()]);
        Optional.ofNullable(path.getParent()).ifPresent(s -> {
            try {
                Files.createDirectories(s);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return Files.newBufferedWriter(path, StandardCharsets.UTF_8, newFileWriteOptionsArray);
    }

    @Override
    public void closeWriters() {
        for (Map.Entry<Path, BufferedWriter> entry : bufferedWriterHashMap.entrySet()) {
            try {
                entry.getValue().close();
            } catch (IOException e) {
                throw new FileWiterException(String.format("Can`t close writer %s", entry.getValue()), e);
            }
        }
    }

}