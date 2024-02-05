package cft.testTask.textFilter.handler;

import cft.testTask.textFilter.config.Configuration;
import cft.testTask.textFilter.util.InputFilesManager;
import cft.testTask.textFilter.util.OutputFilesManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Handler {
    private final Configuration configuration;


    public Handler(Configuration configuration) throws IOException {
        this.configuration = configuration;
    }

    public void handleLine (Configuration configuration, String line) throws IOException{
        OutputFilesManager outputFilesManager = new OutputFilesManager(configuration.getAddToExistFiles());
        Object value = getValueByLine(line);
        Path stringFilePath = Path.of(configuration.getFilePath() + "strings.txt");
        Path floatFilePath = Path.of(configuration.getFilePath() + "floats.txt");
        Path integerFilePath = Path.of(configuration.getFilePath() + "integer.txt");

        if (value instanceof Long){
            outputFilesManager.writeLineIntoFile(integerFilePath,line);
        }else if (value instanceof Double){
            outputFilesManager.writeLineIntoFile(floatFilePath,line);
        }else {
            outputFilesManager.writeLineIntoFile(stringFilePath,line);
        }

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

    public String getStringFromFile(BufferedReader br) throws IOException {
        return br.readLine();
    }

    public int getNumberFromFile(BufferedReader br) throws IOException {
        return Integer.parseInt(br.readLine());
    }
}