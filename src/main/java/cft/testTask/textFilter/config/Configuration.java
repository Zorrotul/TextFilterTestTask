package cft.testTask.textFilter.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Configuration {

    private final StatisticMode statisticMode;// TODO: ридумать как сделать переменные final
    private final String filePath;
    private final String filePrefix;
    private final List<String> inputFileNames = new ArrayList<>();
    private boolean addToExistFiles;
    AtomicInteger i;


    public Configuration(String[] args) {
        StatisticMode statisticMode = StatisticMode.SHORT;
        String filePath = "";
        String filePrefix = "";
        int indexOfFilenames = 0;
        boolean addToExistFiles = false;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-f")) {
                statisticMode = StatisticMode.FULL;
                indexOfFilenames++;
            } else if (args[i].equals("-s")) {
                indexOfFilenames++;
            }
            if (args[i].equals("-o")) {
                indexOfFilenames += 2;
                filePath = args[i + 1];
            }
            if (args[i].equals("-p")) {
                indexOfFilenames += 2;
                filePrefix = args[i + 1];
            }
            if (args[i].equals("-a")) {
                indexOfFilenames++;
                addToExistFiles = true;
            }
        }

        this.statisticMode = statisticMode;
        this.filePath = filePath;
        this.filePrefix = filePrefix;
        this.addToExistFiles = addToExistFiles;

        for (int i = indexOfFilenames; i < args.length; i++) {
            inputFileNames.add(args[i]);
        }
    }

    public void printConfig() {
        System.out.println("StatisticMode: " + statisticMode + "\n"
                + "добавочный путь: " + filePath + "\n"
                + "Префик файлов: " + filePrefix + "\n"
                + "Входящие файлы: " + inputFileNames.toString() + "\n"
                + "Перезатирать ли файлы: " + addToExistFiles);
    }

    public List<String> getInputFileNames() {
        return inputFileNames;
    }

    public StatisticMode getstatisticMode() {
        return statisticMode;
    }

    public String getFilePath() {
        return filePath;
    }

    public StatisticMode getSortMode() {
        return statisticMode;
    }

    public String getFilePrefix() {
        return filePrefix;
    }

    public boolean getAddToExistFiles() {
        return addToExistFiles;
    }

//    private StatisticMode extractStatisticMode(String[] args) {
//        StatisticMode statisticMode = StatisticMode.SHORT;
//
//
//        for (String arg : args) {
//            if (arg.equals("-f")) {
//                statisticMode = StatisticMode.FULL;
//                break;
//            }
//        }
//
//
//        return statisticMode;
//    }


}
