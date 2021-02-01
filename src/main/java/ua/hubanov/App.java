package ua.hubanov;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * DateTime API home work.
 *
 */
public class App {
    public static final LocalDate startOfYear1900 = LocalDate.of(1900, 1, 1);   // 1 Января 1900
    public static final LocalDate startOfYear2000 = LocalDate.of(2000, 1, 1);   // 1 Января 2000
    public static final LocalDate today = LocalDate.now();     // today
    public static final int yearsFrom1900ToNowCount = Year.from(today).getValue() - Year.from(startOfYear1900).getValue();
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH);
    public static final TemporalAdjuster lastDayOfMonth = TemporalAdjusters.lastDayOfMonth();

    public static void main(String[] args) {
//        fridays13().forEach(System.out::println);
//        endOnSundays().forEach(System.out::println);
//        birthdaysOnSaturdays(LocalDate.of(1992,4,30)).forEach(System.out::println);
        daysNotWith24Hours(Year.of(2000), ZoneId.of("Africa/Nairobi")).forEach(System.out::println);
//        System.out.println(countYearsWhenClockShifted(ZoneId.of("Africa/Nairobi")));
//        System.out.println(yearsFrom1900ToNowCount);
//        zonesChangedClockShiftRules(getAllAvailableZoneIds()).forEach(x -> System.out.println(x.toString()));
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

    // Task 5
    public static List<ZoneId> zonesAlwaysClockShift(List<ZoneId> zones) {
        List<ZoneId> result = new ArrayList<>();

        for (ZoneId zoneId : zones) {
            if (countYearsWhenClockShifted(zoneId) == yearsFrom1900ToNowCount) {
                result.add(zoneId);
            }
        }

        return result;
    }

    // Task 5
    public static List<ZoneId> zonesNeverClockShift(List<ZoneId> zones) {
        List<ZoneId> result = new ArrayList<>();

        for (ZoneId zoneId : zones) {
            if (countYearsWhenClockShifted(zoneId) == 0) {
                result.add(zoneId);
            }
        }

        return result;
    }

    // Task 5
    public static List<ZoneId> zonesChangedClockShiftRules(List<ZoneId> zones) {
        List<ZoneId> result = new ArrayList<>();

        for (ZoneId zoneId : zones) {
            int yearsWhenClockShiftedCount = countYearsWhenClockShifted(zoneId);
            if (yearsWhenClockShiftedCount > 0 && yearsWhenClockShiftedCount < yearsFrom1900ToNowCount) {
                result.add(zoneId);
            }
        }

        return result;
    }

    // Task 5
    public static int countYearsWhenClockShifted(ZoneId zoneId) {
        int yearsWhenClockShiftedCount = 0;
        LocalDate iterationDate = startOfYear1900;

        while (iterationDate.isBefore(today)) {
            List<MonthDay> daysNotWith24Hours = daysNotWith24Hours(Year.from(iterationDate), zoneId);
            if (!daysNotWith24Hours.isEmpty()) {
                yearsWhenClockShiftedCount++;
            }
            iterationDate = iterationDate.plusYears(1);
        }
        return yearsWhenClockShiftedCount;
    }



    public static List<ZoneId> getAllAvailableZoneIds() {
        List<ZoneId> result = new ArrayList<>();
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        for (String id : zoneIds) {
            ZoneId zoneId = ZoneId.of(id);
            result.add(zoneId);
        }
        return result;
    }
}
