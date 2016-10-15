package rs.veselinromic.model;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.OffsetTime;

public class ScheduleItem
{
    public String title;
    public DayOfWeek dayOfWeek;
    public OffsetTime time;
    public String simulcastUrl;

    public ScheduleItem(String title, DayOfWeek dayOfWeek, OffsetTime time, String simulcastUrl)
    {
        this.title = title;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
        this.simulcastUrl = simulcastUrl;
    }
}
