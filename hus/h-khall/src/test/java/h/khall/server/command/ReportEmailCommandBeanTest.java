package h.khall.server.command;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class ReportEmailCommandBeanTest
{
  @SuppressWarnings("deprecation")
  @Test
  public void test()
  {
    int[] range = ReportEmailCommandBean.range();

    Date d = new Date();
    int yT = d.getYear() + 1900;
    int mT = d.getMonth();

    d = new Date(d.getTime() - 18408222000L);
    int yF = d.getYear() + 1900;
    int mF = d.getMonth() + 1;

    Assert.assertEquals(yF, range[0]);
    Assert.assertEquals(mF, range[1]);
    Assert.assertEquals(yT, range[2]);
    Assert.assertEquals(mT, range[3]);
  }
}