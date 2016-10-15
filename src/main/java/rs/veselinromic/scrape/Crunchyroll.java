package rs.veselinromic.scrape;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import rs.veselinromic.model.ScheduleItem;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Crunchyroll implements Scraper
{
    @Override
    public List<ScheduleItem> scrape() throws IOException
    {
        List<ScheduleItem> scheduleItems = new ArrayList<>();

        Document schedulePage = Jsoup.connect("http://www.crunchyroll.com/simulcastcalendar").get();

        int currentDay = 0;

        for(Element dayColumn : schedulePage.select("section.calendar-day"))
        {
            currentDay++;

            for(Element release : dayColumn.select("article.release"))
            {
                OffsetDateTime offsetDateTime = OffsetDateTime.parse(
                        release.select("time.available-time").first().attr("datetime"), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                String title = release.select("h1.season-name cite").text();
                String url = release.select("js-season-name-link").attr("href");

                scheduleItems.add(new ScheduleItem(title, offsetDateTime.getDayOfWeek(), offsetDateTime.toOffsetTime(), url));
            }
        }

        return scheduleItems;
    }
}
