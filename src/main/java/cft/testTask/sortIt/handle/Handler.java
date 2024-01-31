package cft.testTask.sortIt.handle;

import cft.testTask.sortIt.config.Configuration;
import cft.testTask.sortIt.config.FileContentType;
import cft.testTask.sortIt.util.ReadersInitializer;
import cft.testTask.sortIt.util.WriterInitializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Handler {
    private final Configuration configuration;
    private final List<BufferedReader> bufferedReaders;


    public Handler(Configuration configuration) throws IOException {
        this.configuration = configuration;
        this.bufferedReaders = ReadersInitializer.getReaders(configuration);
    }


    public String getStringFromFile(BufferedReader br) throws IOException {
        return br.readLine();
    }

    public int getNumberFromFile(BufferedReader br) throws IOException {
        return Integer.parseInt(br.readLine());
    }

    public void sort() throws IOException {

        ArrayList<String> stringFromFiles = new ArrayList<>();
        ArrayList<Integer> intFromFiles = new ArrayList<>();
        int fileIndex = 0;
        if (configuration.getFileContentType().equals(FileContentType.INTEGER)) {

            try (Writer outputWriter = WriterInitializer.createFileAndGetWriter(Path.of(configuration.getOutputFileName()))) {
                int bufferedReadersSize = bufferedReaders.size();
                for (int i = 0; i <= bufferedReadersSize - 1; i++) {
                    String a = bufferedReaders.get(i).readLine();
                    if (a!=null) intFromFiles.add(Integer.parseInt(a));
                    else bufferedReaders.remove(i);
                }
                int writtenToFileIntValue = intFromFiles.get(0);
                while (!bufferedReaders.isEmpty()) {
                    for (int i = 0; i <= bufferedReaders.size() - 1; i++) {
                        if (configuration.getSortMode().compareIntFunc.apply(intFromFiles.get(i), writtenToFileIntValue)) {
                            writtenToFileIntValue = intFromFiles.get(i);
                            fileIndex = i;
                        }
                    }
                    outputWriter.write(writtenToFileIntValue + "\n");
                    String nextLine = bufferedReaders.get(fileIndex).readLine();
                    if (nextLine != null) {
                        intFromFiles.set(fileIndex, Integer.parseInt(nextLine));
                        writtenToFileIntValue = Integer.parseInt(nextLine);
                    } else bufferedReaders.remove(fileIndex);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (configuration.getFileContentType().equals(FileContentType.STRING)) {

            try (Writer outputWriter = WriterInitializer.createFileAndGetWriter(Path.of(configuration.getOutputFileName()))) {
                int bufferedReadersSize = bufferedReaders.size(); // так как будем удалять файлы размер коллекции может измениться
                for (int i = 0; i <= bufferedReadersSize - 1; i++) {
                    String s = getStringFromFile(bufferedReaders.get(i));
                    if (s != null) stringFromFiles.add(s); // Формируем коллекцию из первых значений из файлов
                    else bufferedReaders.remove(i); // Удаляем пустые файлы
                }

                String writtenToFileStringValue = stringFromFiles.get(0);
                while (!bufferedReaders.isEmpty()) {
                    for (int i = 0; i <= bufferedReaders.size() - 1; i++) {
                        if (configuration.getSortMode().compareStringFunc.apply(stringFromFiles.get(i), writtenToFileStringValue)) {
                            writtenToFileStringValue = stringFromFiles.get(i);
                            fileIndex = i;
                        }
                    }

                    outputWriter.write(writtenToFileStringValue + "\n");
                    String nextLine = bufferedReaders.get(fileIndex).readLine();
                    if (nextLine != null) {
                        stringFromFiles.set(fileIndex, nextLine);
                        writtenToFileStringValue = nextLine;
                    } else bufferedReaders.remove(fileIndex);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



