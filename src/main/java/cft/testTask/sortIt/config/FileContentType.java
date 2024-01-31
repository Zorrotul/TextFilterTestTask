package cft.testTask.sortIt.config;

import java.util.stream.Stream;

public enum FileContentType {
    STRING("-s"),
    INTEGER("-i");

    final String code;

    FileContentType(String code){
        this.code =code;
    }

    public static FileContentType getFileContentType(String code) {
        return Stream.of(FileContentType.values())
                .filter(fileContentType -> fileContentType.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no supported type " + code));
    }

}
