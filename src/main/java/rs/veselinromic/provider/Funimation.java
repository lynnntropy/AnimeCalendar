package rs.veselinromic.provider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import rs.veselinromic.model.ScheduleItem;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Funimation implements Provider
{
    @Override
    public List<ScheduleItem> retrieveSchedule() throws IOException
    {
        List<ScheduleItem> scheduleItems = new ArrayList<>();

        Document schedulePage = Jsoup.connect("http://www.funimation.com/shows").get();

        for(Element dayElement : schedulePage.select("article.simulcast_schedule div.item-cell.two"))
        {
            DayOfWeek dayOfWeek = abbeviationToDayOfWeek(dayElement.select("span.day").first().text());

            for(Element releaseElement : dayElement.select("div.left > p"))
            {
                String title = releaseElement.select("a.item-title").text();
                String url = releaseElement.select("a.item-title").attr("href");

                String[] timeStringWords = releaseElement.select("span").text().split(" ");
                String timeString = timeStringWords[0] + " " + timeStringWords[1];
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm aa");
                OffsetTime offsetTime = OffsetTime.parse(timeString, dateTimeFormatter);

                scheduleItems.add(new ScheduleItem(title, dayOfWeek, offsetTime, url));
            }
        }

        return scheduleItems;
    }

    private DayOfWeek abbeviationToDayOfWeek(String abbreviation)
    {
        switch (abbreviation)
        {
            case "MON":
                return DayOfWeek.MONDAY;

            case "TUE":
                return DayOfWeek.TUESDAY;

            case "WED":
                return DayOfWeek.WEDNESDAY;

            case "THU":
                return DayOfWeek.THURSDAY;

            case "FRI":
                return DayOfWeek.FRIDAY;

            case "SAT":
                return DayOfWeek.SATURDAY;

            case "SUN":
                return DayOfWeek.SUNDAY;
        }

        return null;
    }
}
