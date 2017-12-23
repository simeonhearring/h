package h.dao.jdbc.statement;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import h.dao.jdbc.MySqlBaseDaoTest;
import h.json.CurriculumCreate;
import h.model.shared.khall.Curriculum;
import h.model.shared.khall.Curriculums;

public class CurriculumSqlTest extends MySqlBaseDaoTest
{
  private CurriculumSql mSql;

  @Before
  public void before()
  {
    mSql = new CurriculumSql(mDataSource);
  }

  // this is the procedure for adding curriculum
  @Test
  public void test()
  {
    Curriculums curr = new Curriculums();
    curr.setCurriculums(CurriculumCreate.create());

    for (Curriculum value : curr.getCurriculums())
    {
      mSql.upsert(value);
    }
  }

  @Test
  public void canSelectByYear()
  {
    Assert.assertEquals(0, mSql.selectByYear(2017).size());
    Assert.assertEquals(true, mSql.selectByYear(2018).size() > 0);
  }

  @SuppressWarnings("deprecation")
  @Test
  public void canSelectByBetween()
  {
    Assert.assertEquals(56,
        mSql.selectBetween(new Date(2018 - 1900, 0, 1), new Date(2018 - 1900, 0, 31)).size());
  }

  @Test
  public void canSelectYears()
  {
    Assert.assertEquals(1, mSql.selectYears().size());
  }

  @SuppressWarnings("deprecation")
  @Test
  public void canUpsert()
  {
    List<Curriculum> results =
        mSql.selectBetween(new Date(2018 - 1900, 0, 1), new Date(2018 - 1900, 0, 7));
    Assert.assertEquals(11, results.size());

    Curriculum model = results.get(0);
    Assert.assertEquals("2018-01-01", model.getDate().toString());
    Assert.assertEquals(null, model.getDurationMinutes());
    Assert.assertEquals("CHAIRMAN", model.getPart().toString());
    Assert.assertEquals("1", model.getSort().toString());
    Assert.assertEquals("MATTHEW 1-3", model.getSource());
    Assert.assertEquals(null, model.getTheme());

    model.setTheme("JUNIT Theme");

    Assert.assertEquals(2, mSql.upsert(model));

    results = mSql.selectBetween(new Date(2018 - 1900, 0, 1), new Date(2018 - 1900, 0, 7));
    model = results.get(0);
    Assert.assertEquals("2018-01-01", model.getDate().toString());
    Assert.assertEquals(null, model.getDurationMinutes());
    Assert.assertEquals("CHAIRMAN", model.getPart().toString());
    Assert.assertEquals("1", model.getSort().toString());
    Assert.assertEquals("MATTHEW 1-3", model.getSource());
    Assert.assertEquals("JUNIT Theme", model.getTheme());

    model.setTheme(null);

    Assert.assertEquals(2, mSql.upsert(model));
  }
}