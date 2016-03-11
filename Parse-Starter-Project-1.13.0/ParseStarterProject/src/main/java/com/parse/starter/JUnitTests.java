package com.parse.starter;
import org.junit.framework.*;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by M. Ly on 2/26/2016.
 */
public class JUnitTests {

    @Test
    public void testRecentEvents(){
        try {
            for (int i = 0; i < 10; i++) {
                List<Event> mostRecent = EventsBundler.recentEvents(i);
            }
        }
        catch (ParseException e) {
            System.out.println("recentEvents caused a ParseException");
        }

    }

    @Test
    public void getEventsByTagsTest() {
        ArrayList<String> tags = new ArrayList<String>();
        tags.add("tag1");
        tags.add("tag2");
        ArrayList<Event> events = EventsBundler.getEventsByTags(tags), 10, true);

        for (int i = 0; i < 10; i++) {
            System.out.println(events.get(i));
        }
    }

    @Test
    public void getFakeEventTest() {
        final String id = "ert5ygtdf";
        Event defaultEvent = EventsBundler.getEvent(id);
        assertThat(defaultEvent.toString().equals("Dummy Convention"));
    }
}
