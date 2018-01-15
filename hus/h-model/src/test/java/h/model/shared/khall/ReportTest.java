package h.model.shared.khall;

import org.junit.Assert;
import org.junit.Test;

public class ReportTest
{
  @Test
  public void cleanWebComment()
  {
    Report r = new Report();

    r.setRemarks("[web saved on:09-10-2017 17:42] my comment");
    r.cleanRemarks();
    Assert.assertEquals("my comment", r.getRemarks());

    r.setRemarks("[web saved on:09-10-2017 17:42]");
    r.cleanRemarks();
    Assert.assertEquals(null, r.getRemarks());

    r.setRemarks("[web saved on:09-10-2017 17:42] ");
    r.cleanRemarks();
    Assert.assertEquals(null, r.getRemarks());
  }
}