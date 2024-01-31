package cft.testTask.sortIt.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriterInitializer {

    public static BufferedWriter createFileAndGetWriter(Path path) throws IOException {
        BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
        return bw;

    }
}
