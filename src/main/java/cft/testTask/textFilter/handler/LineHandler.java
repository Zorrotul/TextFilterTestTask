package cft.testTask.textFilter.handler;

import java.util.function.Consumer;

public interface LineHandler extends Consumer<String> {

    void accept(String line);

    void endHandle();

}