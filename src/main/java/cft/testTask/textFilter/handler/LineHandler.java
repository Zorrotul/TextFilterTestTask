package cft.testTask.textFilter.handler;

import cft.testTask.textFilter.config.Configuration;
import cft.testTask.textFilter.counters.Counters;
import cft.testTask.textFilter.counters.DoubleCounters;
import cft.testTask.textFilter.counters.LongCounters;
import cft.testTask.textFilter.counters.StringCounters;
import cft.testTask.textFilter.util.OutputFilesManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class LineHandler implements Consumer<String> {

    private final Map<Object,Counters> counters = new HashMap<>();
    private final OutputFilesManager outputFilesManager;
    private final Path stringFilePath;
    private final Path floatFilePath;
    private final Path integerFilePath;

    public LineHandler(Configuration configuration) throws IOException {
        this.outputFilesManager = new OutputFilesManager(configuration.getAddToExistFiles());
        this.stringFilePath = Path.of(configuration.getFilePath() + "strings.txt");
        this.floatFilePath = Path.of(configuration.getFilePath() + "floats.txt");
        this.integerFilePath = Path.of(configuration.getFilePath() + "integer.txt");
    }

    public Object getValueByLine(String line) {
        Object result = null;

        try {
            result = Long.valueOf(line);
        } catch (NumberFormatException nfe) {
            //Не целое число, мб, это вещественное
            try {
                result = Double.valueOf(line);
            } catch (NumberFormatException nfe2) {
                // значит, это строка
                result = line;
            }
        }

        //System.out.printf("result=[%s], clazz=[%s]%n", result, result.getClass());
        return result;
    }

    public void endHandle() throws IOException{
        for (Map.Entry<Object,Counters> entry: counters.entrySet()){
            entry.getValue().printResult();
            System.out.println();
        }
        outputFilesManager.closeWriters();
    }

    public String getStringFromFile(BufferedReader br) throws IOException {
        return br.readLine();
    }

    public int getNumberFromFile(BufferedReader br) throws IOException {
        return Integer.parseInt(br.readLine());
    }

    @Override
    public void accept(String line) {
        Object value = getValueByLine(line);

        if (value instanceof Long) {
            outputFilesManager.writeLineIntoFile(integerFilePath, value);
            counters.computeIfAbsent(value.getClass(),s->new LongCounters()).incCounter(value);
        } else if (value instanceof Double) {
            outputFilesManager.writeLineIntoFile(floatFilePath, value);
            counters.computeIfAbsent(value.getClass(),s->new DoubleCounters()).incCounter(value);
        } else {
            outputFilesManager.writeLineIntoFile(stringFilePath, value);
            counters.computeIfAbsent(value.getClass(),s->new StringCounters()).incCounter(value);
        }
    }
}