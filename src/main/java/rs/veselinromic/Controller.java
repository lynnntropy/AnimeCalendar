package rs.veselinromic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.veselinromic.model.ScheduleItem;
import rs.veselinromic.scrape.Crunchyroll;

import java.io.IOException;
import java.util.List;

@RestController
public class Controller
{
    @RequestMapping("/schedule/crunchyroll")
    public List<ScheduleItem> getCrunchyroll() throws IOException
    {
        return new Crunchyroll().scrape();
    }

    @RequestMapping("/hello")
    String hello()
    {
        return "Hello!";
    }
}
