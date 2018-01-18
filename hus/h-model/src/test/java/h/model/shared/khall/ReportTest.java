package h.model.shared.khall;

import java.util.ArrayList;
import java.util.List;

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

  @Test
  public void isReactivated()
  {
    List<Report> reports = new ArrayList<>();
    reports.add(newReport(1));
    reports.add(newReport(0));
    reports.add(newReport(0));
    reports.add(newReport(0));
    reports.add(newReport(0));

    Assert.assertEquals(true, Reports.gTotal(reports).isReactivated());

    reports.add(newReport(1));
    Assert.assertEquals(false, Reports.gTotal(reports).isReactivated());
  }

  private Report newReport(int inHours)
  {
    Report ret = new Report();
    ret.setHours(inHours);
    return ret;
  }
}