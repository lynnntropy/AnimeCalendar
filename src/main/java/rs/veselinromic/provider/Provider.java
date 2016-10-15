package rs.veselinromic.provider;

import rs.veselinromic.model.ScheduleItem;

import java.io.IOException;
import java.util.List;

public interface Provider
{
    List<ScheduleItem> retrieveSchedule() throws IOException;
}
