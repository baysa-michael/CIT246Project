package cs246.businesscalendar.utilities;

import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class DailyTimeBlocks {
    public static List<LocalTime> dailyCalendarSetup() {
        List<LocalTime> dailyCalendar = new ArrayList<>();

        for (int i = 0; i < 96; i++) {
            dailyCalendar.add(new LocalTime((i / 4), (i % 4 * 15)));
        }

        return dailyCalendar;
    }
}
