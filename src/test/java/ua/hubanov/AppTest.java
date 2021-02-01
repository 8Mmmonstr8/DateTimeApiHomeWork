package ua.hubanov;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import java.time.*;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void fridays13Test() {
        List<String> list = App.fridays13();
        Assert.assertEquals("Oct 2000", list.get(0));
        Assert.assertEquals("Apr 2001", list.get(1));
        Assert.assertEquals("Mar 2020", list.get(35));
        Assert.assertEquals("Nov 2020", list.get(36));
    }

    @Test
    public void endOnSundaysTest() {
        List<YearMonth> list = App.endOnSundays();
        Assert.assertEquals(YearMonth.of(2000, 4), list.get(0));
        Assert.assertEquals(YearMonth.of(2000, 12), list.get(1));
        Assert.assertEquals(YearMonth.of(2020, 5), list.get(33));
        Assert.assertEquals(YearMonth.of(2021, 1), list.get(34));
    }

    @Test
    public void birthdaysOnSaturdays_30_april_1992Test() {
        List<Year> list = App.birthdaysOnSaturdays(LocalDate.of(1992,4,30));
        Assert.assertEquals("2005", list.get(0).toString());
        Assert.assertEquals("2011", list.get(1).toString());
        Assert.assertEquals("2016", list.get(2).toString());
    }

    // ZoneId is "Africa/Nairobi"
    @Test
    public void daysNotWith24Hours_year2000Test() {
        List<MonthDay> monthDays = App.daysNotWith24Hours(Year.of(2000), ZoneId.of("Africa/Nairobi"));
        Assert.assertEquals(0, monthDays.size());
    }

    // ZoneId is Default (Helsinki)
    @Test
    public void daysNotWith24Hours_year2004Test() {
        List<MonthDay> monthDays = App.daysNotWith24Hours(Year.of(2004));
        Assert.assertTrue(monthDays.contains(MonthDay.of(3,28)));
        Assert.assertTrue(monthDays.contains(MonthDay.of(10,31)));
        Assert.assertEquals(2, monthDays.size());

    }
}
