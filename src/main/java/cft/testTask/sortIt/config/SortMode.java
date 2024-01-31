package cft.testTask.sortIt.config;

import java.util.function.BiFunction;
import java.util.stream.Stream;

public enum SortMode {
    ASC("-a", (a, b) -> a.compareTo(b) <= 0 ? true : false, (a,b)-> a <= b ? true : false),
    DESC("-d", (a, b) -> a.compareTo(b) <= 0 ? false : true, (a,b)-> a < b ? false: true);

    final String code;

    final public BiFunction<Integer, Integer, Boolean> compareIntFunc;
    final public BiFunction<String, String, Boolean> compareStringFunc;

    SortMode(String code,BiFunction<String,String,Boolean> compareString, BiFunction<Integer, Integer, Boolean> compare) {
        this.code = code;
        this.compareIntFunc = compare;
        this.compareStringFunc = compareString;
    }

    public static SortMode getSortModeByCode(String code) {
        return Stream.of(SortMode.values())
                .filter(sortMode -> sortMode.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no supported mode " + code));
    }
}//