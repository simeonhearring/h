package h.dao.jdbc.statement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import h.dao.jdbc.MySqlBaseDaoTest;
import h.model.shared.khall.Hall;
import h.model.shared.khall.StudyPoint;

public class ScheduleSqlTest extends MySqlBaseDaoTest
{
  private ScheduleSql mSql;

  @Before
  public void before()
  {
    mSql = new ScheduleSql(mDataSource);
  }

  // this is the procedure for upserting schedules from curriculum.
  @Test
  public void test()
  {
    int congId = 60;
    int[] mos =
    {
        4
    }; // 1=Jan, 2=Feb

    for (int mo : mos)
    {
      mSql.upsert(congId, Hall.MAIN, 2018, mo);

      // for (Part value : Part.student())
      // {
      // mSql.upsert(congId, Hall.SECOND, 2018, mo, value);
      // }
    }
  }

  // @Test
  public void canSelectByYear()
  {
    Assert.assertEquals(133, mSql.select(1, 2018).size());
  }

  // @Test
  public void canSelectByYearMonth()
  {
    Assert.assertEquals(73, mSql.select(1, 2018, 1).size());
  }

  // @Test
  public void canAssign()
  {
    Assert.assertEquals(1, mSql.update(3L, 4L, StudyPoint.SP_10, 4178L));
  }
}