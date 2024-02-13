package cft.testTask.textFilter.handler;

import cft.testTask.textFilter.config.Configuration;
import cft.testTask.textFilter.config.StatisticMode;
import cft.testTask.textFilter.counters.Counters;
import cft.testTask.textFilter.counters.DoubleCounters;
import cft.testTask.textFilter.counters.LongCounters;
import cft.testTask.textFilter.counters.StringCounters;
import cft.testTask.textFilter.util.OutputFilesManager;
import cft.testTask.textFilter.util.OutputFilesManagerImpl;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class LineHandlerImpl implements LineHandler {

    private final Map<Object, Counters> counters = new HashMap<>();
    private final OutputFilesManager outputFilesManager;
    private final Path stringOutputFilePath;
    private final Path floatOutputFilePath;
    private final Path integerOutputFilePath;
    private final StatisticMode statisticMode;


    public LineHandlerImpl(Configuration configuration) {
        this.statisticMode = configuration.getStatisticMode();

        this.outputFilesManager = new OutputFilesManagerImpl(configuration.getAddToExistFiles());

        this.stringOutputFilePath = Path.of(configuration.getOutputFilePath(),
                configuration.getOutputFilePrefix() + "strings.txt");

        this.floatOutputFilePath = Path.of(configuration.getOutputFilePath(),
                configuration.getOutputFilePrefix() + "floats.txt");

        this.integerOutputFilePath = Path.of(configuration.getOutputFilePath(),
                configuration.getOutputFilePrefix() + "integer.txt");
    }

    @Override
    public void accept(String line) {
        Object value = getValueByLine(line);
        if (value instanceof Double) {
            outputFilesManager.writeLineIntoFile(floatOutputFilePath, value);
            counters.computeIfAbsent(value.getClass(), s -> new DoubleCounters(statisticMode)).incCounter(value);
        } else if (value instanceof Long) {
            outputFilesManager.writeLineIntoFile(integerOutputFilePath, value);
            counters.computeIfAbsent(value.getClass(), s -> new LongCounters(statisticMode)).incCounter(value);
        } else {
            outputFilesManager.writeLineIntoFile(stringOutputFilePath, value);
            counters.computeIfAbsent(value.getClass(), s -> new StringCounters(statisticMode)).incCounter(value);
        }
    }

    @Override
    public void endHandle() {
        for (Map.Entry<Object, Counters> entry : counters.entrySet()) {
            entry.getValue().printResult();
            System.out.println();
        }
        outputFilesManager.closeWriters();
    }

    private Object getValueByLine(String line) {
        try {
            return Long.valueOf(line);
        } catch (NumberFormatException nfe) {
            //Не целое число, озможно, это вещественное
            try {
                return Double.valueOf(line);
            } catch (NumberFormatException nfe2) {
                // значит, это строка
                return line;
            }
        }
    }

}