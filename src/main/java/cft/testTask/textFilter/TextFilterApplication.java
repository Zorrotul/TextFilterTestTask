package cft.testTask.textFilter;

import cft.testTask.textFilter.config.Configuration;
import cft.testTask.textFilter.error.ConfigurationException;
import cft.testTask.textFilter.error.FileReaderException;
import cft.testTask.textFilter.error.FileWiterException;
import cft.testTask.textFilter.util.InputFilesManager;
import cft.testTask.textFilter.util.InputFilesManagerImpl;

import java.io.IOException;

public class TextFilterApplication {

    public static void main(String[] args) throws IOException {

        try {
            Configuration configuration = new Configuration(args);

            try (InputFilesManager inputFilesManager = new InputFilesManagerImpl(configuration)) {
                inputFilesManager.filterFiles();
            }
            catch (FileReaderException | FileWiterException | IOException e){
                System.out.println(e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (ConfigurationException configurationException) {
            System.out.println(configurationException.getMessage());
        }

    }
}

