package h.model.shared.khall;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import h.model.shared.khall.Report.PubRange;

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

  @Test
  public void testPubRange()
  {
    Reports r = new Reports();
    r.addReports(reports());

    List<YrMo> yml = YrMo.past(2017, 12, 24);

    PubRange range = r.gPubRange(1L, yml);
    Assert.assertEquals(24, range.getHours().length);
    Assert.assertEquals(50.0, range.getHours()[0].doubleValue(), 0.0);
    Assert.assertEquals(50.0, range.gAverage(3, range.getHours()), 0.0);

    List<Long> pubIds = new ArrayList<>();
    pubIds.add(1L);

    range = r.gPubRange(pubIds, yml);
    Assert.assertEquals(24, range.getHours().length);
    Assert.assertEquals(50.0, range.getHours()[0].doubleValue(), 0.0);
    Assert.assertEquals(50.0, range.gAverage(3, range.getHours()), 0.0);
  }

  @Test
  public void testPubsRange()
  {
    Reports r = new Reports();
    r.addReports(reports());

    List<Long> pubIds = new ArrayList<>();
    pubIds.add(1L);
    pubIds.add(2L);
    pubIds.add(3L);

    List<YrMo> yml = YrMo.past(2017, 12, 24);

    PubRange range = r.gPubRange(pubIds, yml);
    Assert.assertEquals(24, range.getHours().length);
    Assert.assertEquals(150.0, range.getHours()[0].doubleValue(), 0.0);
    Assert.assertEquals(50.0, range.gAverage(3, range.getHours()), 0.0);
  }

  private List<Report> reports()
  {
    List<Report> reports = new ArrayList<>();
    int[] pubId = {1,2,3,4,5};
    int[] year = {2016,2017};
    int[] month = {1,2,3,4,5,6,7,8,9,10,11,12};

    for (int pubid : pubId)
    {
      for (int yr : year)
      {
        for (int mo : month)
        {
          reports.add(newReport(pubid, 50, yr, mo));
        }
      }
    }
    return reports;
  }

  private Report newReport(int inHours)
  {
    Report ret = new Report();
    ret.setHours(inHours);
    return ret;
  }

  private Report newReport(long inPubId, int inHours, int inYear, int inMonth)
  {
    Report ret = new Report();
    ret.setPubId(inPubId);
    ret.setHours(inHours);
    ret.setYear(inYear);
    ret.setMonth(inMonth);
    return ret;
  }
}