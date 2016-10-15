package rs.veselinromic.scrape;

import rs.veselinromic.model.ScheduleItem;

import java.io.IOException;
import java.util.List;

public interface Scraper
{
    List<ScheduleItem> scrape() throws IOException;
}
