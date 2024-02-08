package cft.testTask.textFilter;

import cft.testTask.textFilter.config.Configuration;
import cft.testTask.textFilter.util.InputFilesManager;
import cft.testTask.textFilter.util.OutputFilesManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Path;

public class TextFilterApplication {

    public static void main(String[] args) throws IOException {

        System.out.println("jar started");
        String[] testArgs = new String[]{"-s", "-a",  "-p", "sample-", "in1.txt", "in2.txt"};
        Configuration configuration = new Configuration(testArgs);

        configuration.printConfig();
//        OutputFilesManager ofm = new OutputFilesManager(true);
//        BufferedWriter br = ofm.createFileAndGetWriter(Path.of("1out.txt"));
//        br.write("Hello");
//        br.close();
        try(InputFilesManager inputFilesManager = new InputFilesManager(configuration)){
            inputFilesManager.filterFiles();

        }catch (IOException ioException){

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

