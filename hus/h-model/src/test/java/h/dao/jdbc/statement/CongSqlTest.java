package h.dao.jdbc.statement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import h.dao.jdbc.MySqlBaseDaoTest;
import h.model.shared.khall.Congregation;
import h.model.shared.khall.Event;
import h.model.shared.khall.FieldServiceGroup;
import h.model.shared.khall.Hall;

public class CongSqlTest extends MySqlBaseDaoTest
{
  private CongSql mSql;

  @Before
  public void before()
  {
    mSql = new CongSql(mDataSource);
  }

  @Test
  public void canUpsert()
  {
    Congregation cong = new Congregation();
    cong.setId(60);
    cong.setNumber("952267");

    cong.setName("Triple Creek - Gallatin, TN");
    cong.setHalls(Hall.MAIN);

    cong.setFsgs(fsgs());
    cong.setEvents(events());

    mSql.upsert(cong);
  }

  private Map<Integer, FieldServiceGroup> fsgs()
  {
    int tunstill = 82; // 87
    int hearring = 83; // 85
    int humphrey = 79; // 86
    int kerstner = 80; // 88
    Map<Integer, FieldServiceGroup> ret = new HashMap<>();
    ret.put(hearring, newFsg(hearring, "Hearring", "1441 Long Hollow Pike, Gallatin TN 37066 @ 9:30 AM"));
    ret.put(humphrey, newFsg(humphrey, "Humphrey", "214 Drakewood Dr. Portland, TN 37148 @ 9:00 AM"));
    ret.put(tunstill, newFsg(tunstill, "Tunstill", "1620 Airport Rd, Gallatin TN 37066 @ 9:00 AM"));
    ret.put(kerstner, newFsg(kerstner, "Kerstner", "249 Santa Monica Blvd, Gallatin TN 37066 @ 9:00 AM"));
    return ret;
  }

  private FieldServiceGroup newFsg(int inId, String inString, String inString2)
  {
    FieldServiceGroup ret = new FieldServiceGroup();
    ret.setId(inId);
    ret.setTitle(inString);
    ret.setLocation(inString2);
    return ret;
  }

  public static Map<Date, Event> events()
  {
    Map<Date, Event> e = new HashMap<>();

    e.put(new Date(1452042000000L), new Event(Event.Type.WEEKMID, null));
    e.put(new Date(1515218400000L), new Event(Event.Type.WEEKEND, null));

    e.put(new Date(1519020000000L), // Mon Feb 19 2018
        new Event(Event.Type.CO_VISIT, "Let Endurance Complete Its Work"));

    e.put(new Date(1533013200000L), // Tue Jul 31 2018
        new Event(Event.Type.CO_VISIT, "We Must Run With Endurance!"));

    e.put(new Date(1524373200000L), // Sun Apr 22 2018
        new Event(Event.Type.CACO, "Don't Give Up In Doing What Is Fine!"));

    e.put(new Date(1528693200000L), // Mon Jun 11 2018
        new Event(Event.Type.REGIONAL, "'Be Courageous'!"));

    e.put(newDate("2018-10-28"), // Oct 28 2018
        new Event(Event.Type.CACO, "TBA"));

    e.put(newDate("2018-12-25"), // Tue Dec 25 2018
        new Event(Event.Type.CO_VISIT, "TBA"));

    e.put(newDate("2019-03-17"), // Mar 17 2019
        new Event(Event.Type.CABR, "TBA"));

    return e;
  }

  private static Date newDate(String inString)
  {
    try
    {
      return new SimpleDateFormat("yyyy-MM-dd").parse(inString);
    }
    catch (ParseException e)
    {
      return null;
    }
  }

  @Test
  public void canSelectUsingNum()
  {
    Congregation cong = mSql.selectByCongNum("952267");
    Assert.assertNotNull(cong);
  }

  @Test
  public void canSelectUsingID()
  {
    Congregation cong = mSql.selectById(60);
    Assert.assertNotNull(cong);
  }
}