package cft.testTask.textFilter.config;

import cft.testTask.textFilter.error.ConfigurationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class Configuration {

    public static final List<Character> prohibitedValues = List.of('/', '*', ':', '\\', '?', '|');
    private final StatisticMode statisticMode;
    private final String outputFilePath;
    private final String outputFilePrefix;
    private final List<String> inputFileNames = new ArrayList<>();
    private final boolean addToExistFiles;

    public Configuration(String[] args) {
        StatisticMode statisticMode = StatisticMode.SHORT;
        String outputFilePath = "";
        String outputFilePrefix = "";
        int startIndexOfInputFilenames = 0;
        boolean addToExistFiles = false;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-f")) {
                statisticMode = StatisticMode.FULL;
                startIndexOfInputFilenames++;
            } else if (args[i].equals("-s")) {
                startIndexOfInputFilenames++;
            }
            if (args[i].equals("-o")) {
                outputFilePath = args[i + 1];
                startIndexOfInputFilenames += 2;
            }
            if (args[i].equals("-p")) {
                outputFilePrefix = Optional.of(args[i + 1])
                        .filter(prefix -> prefix.chars().anyMatch(letter -> prohibitedValues.contains((char) letter)))
                        .map(prefix -> {
                            System.out.println("В префиксе " + prefix + " не должно содержаться данных символов: / * : \\ ? |");
                            for (char p : prohibitedValues) {
                                prefix = prefix.replace(String.valueOf(p), "");
                            }
                            System.out.println("Префикс файлов стал: " + prefix);
                            return prefix;
                        })
                        .orElse(args[i + 1]);

                startIndexOfInputFilenames += 2;
            }
            if (args[i].equals("-a")) {
                addToExistFiles = true;
                startIndexOfInputFilenames++;
            }
        }

        this.statisticMode = statisticMode;
        this.outputFilePath = outputFilePath;
        this.outputFilePrefix = outputFilePrefix;
        this.addToExistFiles = addToExistFiles;

        for (int i = startIndexOfInputFilenames; i < args.length; i++) {
            fileNameValidator(args[i]);
        }

        Optional.of(inputFileNames)
                .filter(List::isEmpty)
                .ifPresent(i -> {
                    String errorMessage = String.format("There is no input files in entered args=[%s]", Arrays.toString(args));
                    throw new ConfigurationException(errorMessage);
                });
    }

    public void printConfig() {
        System.out.println("StatisticMode: " + statisticMode + System.lineSeparator()
                + "добавочный путь: " + outputFilePath + System.lineSeparator()
                + "Префикс файлов: " + outputFilePrefix + System.lineSeparator()
                + "Входящие файлы: " + inputFileNames + System.lineSeparator()
                + "Перезатирать ли файлы: " + addToExistFiles);
    }

    public List<String> getInputFileNames() {
        return inputFileNames;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public String getOutputFilePrefix() {
        return outputFilePrefix;
    }

    public StatisticMode getStatisticMode() {
        return statisticMode;
    }

    public boolean getAddToExistFiles() {
        return addToExistFiles;
    }

    private void fileNameValidator(String name) {
        Optional.of(name)
                .filter(fileName -> fileName.chars().noneMatch(letter -> prohibitedValues.contains((char) letter)))
                .ifPresentOrElse(inputFileNames::add, () -> {
                    System.out.println("Error: В имени файла (" + name
                            + ") не должно содержаться данных символов: / * : \\ ? |"
                            + System.lineSeparator() + "Данный файл не будет обработан");

                });
    }

}
