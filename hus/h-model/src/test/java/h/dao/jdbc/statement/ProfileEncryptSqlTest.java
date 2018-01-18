package h.dao.jdbc.statement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import h.dao.jdbc.MySqlBaseDaoTest;
import h.model.shared.Profile.Type;
import h.model.shared.khall.Assignments.Count;
import h.model.shared.khall.Profile;

public class ProfileEncryptSqlTest extends MySqlBaseDaoTest
{
  private ProfileEncryptSql mSql;

  @Before
  public void before()
  {
    mSql = new ProfileEncryptSql(mDataSource);
  }

  @Test
  public void test()
  {
    Profile model = new Profile();
    model.setCongNum("952267");
    model.setCongId(60);
    model.setCount(Count.STUDENT);
    model.setFirst("Simeon");
    model.setType(Type.USER);
    model.setUserId("simeonlhearring@gmail.com");
    model.setUserTitle("Owner");
    model.setLast("Hearring");
    model.setPassword("abc");
    model.setThreshold(5.0);

    mSql.upsert(model);

    mSql.upsert("NBgh(epoT(MSkV77kFaCE~cC_SSf7c7o", model);

    Profile p = mSql.select("NBgh(epoT(MSkV77kFaCE~cC_SSf7c7o", "simeonlhearring@gmail.com");
    Assert.assertNotNull(p);
    Assert.assertEquals("Simeon", p.getFirst());
  }
}