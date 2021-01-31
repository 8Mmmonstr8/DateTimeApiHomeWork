package ua.hubanov;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public static void main(String[] args) {
        fridays13().forEach(System.out::println);
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
}
