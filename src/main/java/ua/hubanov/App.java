package ua.hubanov;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * DateTime API home work.
 *
 */
public class App {
    public static final LocalDate startOfYear2000 = LocalDate.of(2000, 1, 1);   // 1 Января 2000
    public static final LocalDate today = LocalDate.now();     // today
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH);
    public static final TemporalAdjuster lastDayOfMonth = TemporalAdjusters.lastDayOfMonth();

    public static void main(String[] args) {
//        fridays13().forEach(System.out::println);
//        endOnSundays().forEach(System.out::println);
//        birthdaysOnSaturdays(LocalDate.of(1992,4,30)).forEach(System.out::println);
        daysNotWith24Hours(Year.of(2001)).forEach(System.out::println);
    }

    // Task 1
    public static List<String> fridays13() {
        List<String> result = new ArrayList<>();
        LocalDate iterationDate = startOfYear2000;

        while (iterationDate.getDayOfMonth() != 13) {
            iterationDate = iterationDate.plusDays(1);
        }

        while (iterationDate.isBefore(today)) {
            if (iterationDate.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                result.add(iterationDate.format(formatter));
            }
            iterationDate = iterationDate.plusMonths(1);
        }

        return result;
    }

    // Task 2
    public static List<YearMonth> endOnSundays() {
        List<YearMonth> result = new ArrayList<>();
        LocalDate iterationDate = startOfYear2000;

        while (iterationDate.isBefore(today)) {
            if (iterationDate.with(lastDayOfMonth).getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                result.add(YearMonth.from(iterationDate));
            }
            iterationDate = iterationDate.plusMonths(1);
        }

        return result;
    }

    // Task 3
    public static List<Year> birthdaysOnSaturdays(LocalDate birthday) {
        List<Year> result = new ArrayList<>();
        LocalDate iterationDate = startOfYear2000;
        MonthDay birthdayMonthAndDay = MonthDay.from(birthday);

        while (iterationDate.isBefore(today)) {
            if (birthdayMonthAndDay.atYear(iterationDate.getYear()).getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
                result.add(Year.from(iterationDate));
            }
            iterationDate = iterationDate.plusYears(1);
        }

        return result;
    }

    // Task 4
    public static List<MonthDay> daysNotWith24Hours(Year year) {
        return daysNotWith24Hours(year, ZoneId.systemDefault());
    }

    // Task 4
    public static List<MonthDay> daysNotWith24Hours(Year year, ZoneId zoneId) {
        List<MonthDay> result = new ArrayList<>();
        ZonedDateTime iterationDate = ZonedDateTime.of(LocalDateTime.of(year.get(ChronoField.YEAR), 1, 1, 0, 0), zoneId);

        for (int i = 1; i < year.length(); i++) {
            if (ChronoUnit.HOURS.between(iterationDate, iterationDate.plusDays(1)) != 24) {
                result.add(MonthDay.from(iterationDate));
            }
            iterationDate = iterationDate.plusDays(1);
        }

        return result;
    }
}
