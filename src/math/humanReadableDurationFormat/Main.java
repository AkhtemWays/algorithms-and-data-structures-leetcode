package math.humanReadableDurationFormat;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    enum TimeDefinition {
        YEAR, DAY, HOUR, MINUTE, SECOND
    }

    static Map<TimeDefinition, Boolean> timeDefinitions = new HashMap<>();

//    public static void main(String[] args) {
//        assert formatDuration(1).equals("1 second");
//        assert formatDuration(62).equals("1 minute and 2 seconds");
//        assert formatDuration(120).equals("2 minutes");
//        assert formatDuration(3600).equals("1 hour");
//        assert formatDuration(3662).equals("1 hour, 1 minute and 2 seconds");
//        assert formatDuration(Integer.MAX_VALUE).equals("68 years, 35 days, 3 hours, 14 minutes and 7 seconds");
//        assert formatDuration(6136025).equals("71 days, 27 minutes and 5 seconds");
//    }

    public static String formatDuration(int seconds) {
        if (seconds < 0) return "";
        if (seconds == 0) return "now";

        StringBuilder res = new StringBuilder();
        boolean isInitial = true;

        int minute = 60;
        int hour = minute * 60;
        int day = hour * 24;
        int year = day * 365;

        int years = seconds / year;
        isInitial = !(years > 0);
        seconds %= year;
        if (years > 1) res.append(years).append(" years");
        else if (years > 0) res.append(years).append(" year");
        timeDefinitions.put(TimeDefinition.YEAR, years > 0);

        int days = seconds / day;
        seconds %= day;
        String sep = getSeparator(TimeDefinition.DAY, seconds, isInitial);
        isInitial = isInitial && !(days > 0);
        if (days > 1) res.append(sep).append(days).append(" days");
        else if (days > 0) res.append(sep).append(days).append(" day");
        timeDefinitions.put(TimeDefinition.DAY, days > 0);

        int hours = seconds / hour;
        seconds %= hour;
        sep = getSeparator(TimeDefinition.HOUR, seconds, isInitial);
        isInitial = isInitial && !(hours > 0);
        if (hours > 1) res.append(sep).append(hours).append(" hours");
        else if (hours > 0) res.append(sep).append(hours).append(" hour");
        timeDefinitions.put(TimeDefinition.HOUR, hours > 0);

        int minutes = seconds / minute;
        seconds %= minute;
        sep = getSeparator(TimeDefinition.MINUTE, seconds, isInitial);
        isInitial = isInitial && !(minutes > 0);
        if (minutes > 1) res.append(sep).append(minutes).append(" minutes");
        else if (minutes > 0) res.append(sep).append(minutes).append(" minute");
        timeDefinitions.put(TimeDefinition.MINUTE, minutes > 0);

        sep = getSeparator(TimeDefinition.SECOND, seconds, isInitial);
        if (seconds > 1) res.append(sep).append(seconds).append(" seconds");
        else if (seconds > 0) res.append(sep).append(seconds).append(" second");

        return res.toString();
    }

    static String getSeparator(TimeDefinition timeDefinition, int seconds, boolean isInitial) {
        switch (timeDefinition) {
            case DAY:
                return (timeDefinitions.get(TimeDefinition.YEAR)) && seconds > 0 ? ", " : (seconds == 0 && !isInitial) ? " and " : "";
            case HOUR:
                return (timeDefinitions.get(TimeDefinition.YEAR) || timeDefinitions.get(TimeDefinition.DAY)) && seconds > 0 ? ", " : (seconds == 0 && !isInitial) ? " and " : "";
            case MINUTE:
                return (timeDefinitions.get(TimeDefinition.YEAR) || timeDefinitions.get(TimeDefinition.DAY) || timeDefinitions.get(TimeDefinition.HOUR)) && seconds > 0 ? ", " : (seconds == 0 && !isInitial) ? " and " : "";
            case SECOND:
                return timeDefinitions.get(TimeDefinition.YEAR) || timeDefinitions.get(TimeDefinition.DAY) || timeDefinitions.get(TimeDefinition.HOUR) || timeDefinitions.get(TimeDefinition.MINUTE) ? " and " : "";
            default:
                return "";
        }
    }
}
