package h.dao.jdbc.statement;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import h.dao.jdbc.MySqlBaseDaoTest;
import h.model.shared.khall.Report;
import h.model.shared.util.TimeUtil;

public class ReportSqlTest extends MySqlBaseDaoTest
{
  private ReportSql mSql;

  @Before
  public void before()
  {
    mSql = new ReportSql(mDataSource);
  }

  @Test
  public void canUpsert()
  {
    Report r = new Report();
    r.setCongId(-1);
    r.setPubId(1L);
    r.setYear(2000);
    r.setMonth(1);
    r.setHours(15);

    Assert.assertEquals(true, mSql.upsert(r) > 0);
  }

  @Test
  public void can()
  {
    List<Report> report = mSql.select(-1, 1);
    Assert.assertEquals(true, report.size() > 0);
  }

  @Test
  public void canS0()
  {
    Date d1 = TimeUtil.getFirstOfMonth(2000, 1);
    Date d2 = TimeUtil.getFirstOfMonth(2000, 2);
    List<Report> report = mSql.select(-1, 1, d1, d2);
    Assert.assertEquals(true, report.size() > 0);
  }

  @Test
  public void canS1()
  {
    Date d1 = TimeUtil.getFirstOfMonth(2000, 1);
    Date d2 = TimeUtil.getFirstOfMonth(2000, 2);
    List<Report> report = mSql.select(-1, d1, d2);
    Assert.assertEquals(true, report.size() > 0);
  }
}