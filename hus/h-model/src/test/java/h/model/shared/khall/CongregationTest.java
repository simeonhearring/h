package h.model.shared.khall;

import java.text.ParseException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.ibm.icu.text.SimpleDateFormat;

import h.dao.jdbc.statement.CongSqlTest;

public class CongregationTest
{
  @Test
  public void test()
  {
    Congregation model = new Congregation();
    model.setEvents(CongSqlTest.events());

    Event e1 = model.gEvent(date("2018-02-12"));
    Assert.assertNull(e1);

    Event e2 = model.gEvent(date("2018-02-19"));
    Assert.assertNotNull(e2);
  }

  private Date date(String inText)
  {
    try
    {
      return new SimpleDateFormat("yyyy-MM-dd").parse(inText);
    }
    catch (ParseException e)
    {
    }
    return null;
  }
}