package rs.veselinromic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.veselinromic.model.ScheduleItem;
import rs.veselinromic.provider.Crunchyroll;
import rs.veselinromic.provider.Funimation;

import java.io.IOException;
import java.util.List;

@RestController
public class Controller
{
    @RequestMapping("/schedule/crunchyroll")
    public List<ScheduleItem> getCrunchyroll() throws IOException
    {
        return new Crunchyroll().retrieveSchedule();
    }

    @RequestMapping("/schedule/funimation")
    public List<ScheduleItem> getFuni() throws IOException
    {
        return new Funimation().retrieveSchedule();
    }

    @RequestMapping("/hello")
    String hello()
    {
        return "Hello!";
    }
}
