package h.dao.jdbc.statement;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import h.dao.jdbc.MySqlBaseDaoTest;
import h.model.shared.khall.Congregation;
import h.model.shared.khall.Event;
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

    mSql.upsert(cong);
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