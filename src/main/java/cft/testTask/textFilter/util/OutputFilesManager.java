package cft.testTask.textFilter.util;

import java.nio.file.Path;

public interface OutputFilesManager {

    void writeLineIntoFile(Path path, Object line);

    void closeWriters();

}