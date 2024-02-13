package cft.testTask.textFilter.config;

public enum StatisticMode {
    SHORT("-s"),
    FULL("-f");

    final String code;

    StatisticMode(String code) {
        this.code = code;
    }

}