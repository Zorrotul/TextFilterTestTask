package cft.testTask.textFilter.config;

import java.util.ArrayList;
import java.util.List;

public class Configuration {

    private StatisticMode statisticMode = StatisticMode.SHORT;// TODO: ридумать как сделать переменные final
    private String filePath = "";
    private String filePrefix = "";
    private List<String> inputFileNames = new ArrayList<>();
    private int indexOfFilenames = 0;
    private boolean addToExistFiles = false;

    public Configuration(String[] args) {

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-f")) {
                this.statisticMode = StatisticMode.FULL;
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
        for (int i = indexOfFilenames; i < args.length; i++) {
            inputFileNames.add(args[i]);
        }

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


}
