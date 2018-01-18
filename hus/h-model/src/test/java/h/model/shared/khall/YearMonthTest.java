package h.model.shared.khall;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class YearMonthTest
{
  @Test
  public void test()
  {
    YrMo model = new YrMo(2018, 1);
    Assert.assertEquals("Jan 18", model.getDisplay());
  }

  @Test
  public void testList()
  {
    List<YrMo> model = YrMo.past(2018, 1, 14);
    Assert.assertEquals(14, model.size());
    Assert.assertEquals("Jan 18", model.get(0).getDisplay());
    Assert.assertEquals("Dec 17", model.get(1).getDisplay());
    Assert.assertEquals("Nov 17", model.get(2).getDisplay());
    Assert.assertEquals("Oct 17", model.get(3).getDisplay());
    Assert.assertEquals("Sep 17", model.get(4).getDisplay());
    Assert.assertEquals("Aug 17", model.get(5).getDisplay());
    Assert.assertEquals("Jul 17", model.get(6).getDisplay());
    Assert.assertEquals("Jun 17", model.get(7).getDisplay());
    Assert.assertEquals("May 17", model.get(8).getDisplay());
    Assert.assertEquals("Apr 17", model.get(9).getDisplay());
    Assert.assertEquals("Mar 17", model.get(10).getDisplay());
    Assert.assertEquals("Feb 17", model.get(11).getDisplay());
    Assert.assertEquals("Jan 17", model.get(12).getDisplay());
    Assert.assertEquals("Dec 16", model.get(13).getDisplay());
  }
}