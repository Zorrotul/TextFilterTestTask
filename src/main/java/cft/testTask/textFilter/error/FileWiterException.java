package cft.testTask.textFilter.error;

public class FileWiterException extends RuntimeException {

    public FileWiterException(String message) {
        super(message);
    }

    public FileWiterException(String message, Throwable cause) {
        super(message, cause);
    }

}