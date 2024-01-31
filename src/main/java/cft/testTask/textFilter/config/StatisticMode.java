package cft.testTask.textFilter.config;

import java.util.function.BiFunction;
import java.util.stream.Stream;

public enum StatisticMode {
    SHORT("-s", (a, b) -> a.compareTo(b) <= 0 ? true : false, (a, b) -> a <= b ? true : false),
    FULL("-f", (a, b) -> a.compareTo(b) <= 0 ? false : true, (a, b) -> a < b ? false : true);

    final String code;

    final public BiFunction<Integer, Integer, Boolean> compareIntFunc;
    final public BiFunction<String, String, Boolean> compareStringFunc;

    StatisticMode(String code, BiFunction<String, String, Boolean> compareString, BiFunction<Integer, Integer, Boolean> compare) {
        this.code = code;
        this.compareIntFunc = compare;
        this.compareStringFunc = compareString;
    }

    public static StatisticMode getSortModeByCode(String code) {
        return Stream.of(StatisticMode.values())
                .filter(statisticMode -> statisticMode.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no supported mode " + code));
    }
}//