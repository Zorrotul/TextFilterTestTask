package cft.testTask.textFilter.config;

import cft.testTask.textFilter.error.ConfigurationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class ConfigurationTest {

    static final String INPUT_FILE1 = "inputFile1.txt";
    static final String INPUT_FILE2 = "inputFile1.txt";
    static final String OUTPUT_FILE_PATH_VALUE = "some/path";
    static final String OUTPUT_FILE_PATH_FLAG = "-o";
    static final String OUTPUT_FILE_PREFIX_VALUE = "sample-";
    static final String OUTPUT_FILE_PREFIX_FLAG = "-p";
    static final String STATISTIC_MODE_FULL = "-f";
    static final String STATISTIC_MODE_SHORT = "-s";
    static final String ADD_TO_EXIST_FILES = "-a";


    @Test
    void initConfigWithOneInputFile() {
        String[] args = new String[]{INPUT_FILE1};
        List<String> expectedInputFileNames = new ArrayList<>(List.of((INPUT_FILE1)));
        Configuration configuration = new Configuration(args);


        assert Objects.equals(configuration.getOutputFilePath(), "");
        assert Objects.equals(configuration.getOutputFilePrefix(), "");
        assert Objects.equals(configuration.getInputFileNames(), expectedInputFileNames);
        assert !configuration.getAddToExistFiles();
    }

    @Test
    void initConfigWithTwoInputFiles() {
        String[] args = new String[]{INPUT_FILE1, INPUT_FILE2};
        List<String> expectedInputFileNames = new ArrayList<>(List.of(INPUT_FILE1, INPUT_FILE2));
        Configuration configuration = new Configuration(args);

        assert Objects.equals(configuration.getOutputFilePath(), "");
        assert Objects.equals(configuration.getOutputFilePrefix(), "");
        assert Objects.equals(configuration.getInputFileNames(), expectedInputFileNames);
        assert !configuration.getAddToExistFiles();
    }

    @Test
    void initConfigWithFilePathAndFileName() {
        String[] args = new String[]{OUTPUT_FILE_PATH_FLAG, OUTPUT_FILE_PATH_VALUE, INPUT_FILE1};
        List<String> expectedInputFileNames = new ArrayList<>(List.of(INPUT_FILE1));
        Configuration configuration = new Configuration(args);

        assert Objects.equals(configuration.getOutputFilePath(), OUTPUT_FILE_PATH_VALUE);
        assert Objects.equals(configuration.getOutputFilePrefix(), "");
        assert Objects.equals(configuration.getInputFileNames(), expectedInputFileNames);
        assert !configuration.getAddToExistFiles();
    }

    @Test
    void initConfigWithFilePathFilePrefixAndFileName() {
        String[] args = new String[]{
                OUTPUT_FILE_PATH_FLAG, OUTPUT_FILE_PATH_VALUE,
                OUTPUT_FILE_PREFIX_FLAG, OUTPUT_FILE_PREFIX_VALUE,
                INPUT_FILE1};
        List<String> expectedInputFileNames = new ArrayList<>(List.of(INPUT_FILE1));
        Configuration configuration = new Configuration(args);

        assert Objects.equals(configuration.getOutputFilePath(), OUTPUT_FILE_PATH_VALUE);
        assert Objects.equals(configuration.getOutputFilePrefix(), OUTPUT_FILE_PREFIX_VALUE);
        assert Objects.equals(configuration.getInputFileNames(), expectedInputFileNames);
        assert !configuration.getAddToExistFiles();
    }

    @Test
    void initConfigWithShortStatisticModeFilePathFilePrefixAndFileName() {
        String[] args = new String[]{
                STATISTIC_MODE_SHORT,
                OUTPUT_FILE_PATH_FLAG, OUTPUT_FILE_PATH_VALUE,
                OUTPUT_FILE_PREFIX_FLAG, OUTPUT_FILE_PREFIX_VALUE,
                INPUT_FILE1};
        List<String> expectedInputFileNames = new ArrayList<>(List.of(INPUT_FILE1));
        Configuration configuration = new Configuration(args);

        assert Objects.equals(configuration.getStatisticMode(), StatisticMode.SHORT);
        assert Objects.equals(configuration.getOutputFilePath(), OUTPUT_FILE_PATH_VALUE);
        assert Objects.equals(configuration.getOutputFilePrefix(), OUTPUT_FILE_PREFIX_VALUE);
        assert Objects.equals(configuration.getInputFileNames(), expectedInputFileNames);
        assert !configuration.getAddToExistFiles();
    }

    @Test
    void initConfigWithShortStatisticModeAddToExistFilesFilePathFilePrefixAndFileName() {
        String[] args = new String[]{
                STATISTIC_MODE_SHORT, ADD_TO_EXIST_FILES,
                OUTPUT_FILE_PATH_FLAG, OUTPUT_FILE_PATH_VALUE,
                OUTPUT_FILE_PREFIX_FLAG, OUTPUT_FILE_PREFIX_VALUE,
                INPUT_FILE1};
        List<String> expectedInputFileNames = new ArrayList<>(List.of(INPUT_FILE1));
        Configuration configuration = new Configuration(args);

        assert Objects.equals(configuration.getStatisticMode(), StatisticMode.SHORT);
        assert Objects.equals(configuration.getOutputFilePath(), OUTPUT_FILE_PATH_VALUE);
        assert Objects.equals(configuration.getOutputFilePrefix(), OUTPUT_FILE_PREFIX_VALUE);
        assert Objects.equals(configuration.getInputFileNames(), expectedInputFileNames);
        assert configuration.getAddToExistFiles();
    }

    @Test
    void initConfigWithFullStatisticModeAddToExistFilesFilePathFilePrefixAndFileName() {
        String[] args = new String[]{
                STATISTIC_MODE_FULL, ADD_TO_EXIST_FILES,
                OUTPUT_FILE_PATH_FLAG, OUTPUT_FILE_PATH_VALUE,
                OUTPUT_FILE_PREFIX_FLAG, OUTPUT_FILE_PREFIX_VALUE,
                INPUT_FILE1};
        List<String> expectedInputFileNames = new ArrayList<>(List.of(INPUT_FILE1));

        Configuration configuration = new Configuration(args);

        assert Objects.equals(configuration.getStatisticMode(), StatisticMode.FULL);
        assert Objects.equals(configuration.getOutputFilePath(), OUTPUT_FILE_PATH_VALUE);
        assert Objects.equals(configuration.getOutputFilePrefix(), OUTPUT_FILE_PREFIX_VALUE);
        assert Objects.equals(configuration.getInputFileNames(), expectedInputFileNames);
        assert configuration.getAddToExistFiles();
    }

    @Test
    void failedInitConfig() {
        String[] args = new String[0];

        ConfigurationException exception = Assertions.assertThrows(
                ConfigurationException.class,
                () -> new Configuration(args)
        );
        assert Objects.equals(exception.getMessage(), "There is no input files in entered args=[[]]");

    }
}


