package h.json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import h.model.shared.khall.Congregation;
import h.model.shared.khall.Event;
import h.model.shared.khall.Hall;

public class CongregationJsonTest
{
  @Test
  public void test()
  {
    Congregation cong = new Congregation();
    cong.setId(60);
    cong.setNumber("952267");

    // cong.setMidweekOn(new Date(1452042000000L));
    cong.setName("Triple Creek - Gallatin, TN");
    cong.setHalls(Hall.MAIN);

    Map<Date, Event> e = new HashMap<>();
    cong.setEvents(e);

    e.put(new Date(1452042000000L), new Event(Event.Type.WEEKMID, null));
    e.put(new Date(1515218400000L), new Event(Event.Type.WEEKEND, null));

    e.put(new Date(1519020000000L),
        new Event(Event.Type.CO_VISIT, "Let Endurance Complete Its Work"));

    e.put(new Date(1533013200000L), new Event(Event.Type.CO_VISIT, "We Must Run With Endurance!"));

    e.put(new Date(1523077200000L),
        new Event(Event.Type.CACO, "Don't Give Up In Doing What Is Fine!"));

    e.put(new Date(1528693200000L), new Event(Event.Type.REGIONAL, "'Be Courageous'!"));


    JsonMapper json = new JsonMapper();
    String text = json.writeValue(cong);
    System.out.println(text);

  }

  private Date newDate(String inText)
  {
    try
    {
      return new SimpleDateFormat("yyyy-MM-dd").parse(inText);
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    return null;
  }
}