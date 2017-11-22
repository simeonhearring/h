package h.dao.jdbc.statement;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import h.dao.jdbc.MySqlBaseDaoTest;
import h.json.PersonCreate;
import h.model.shared.khall.Person;

public class PersonEncryptSqlTest extends MySqlBaseDaoTest
{
  private PersonEncryptSql mSql;

  @Before
  public void before()
  {
    mSql = new PersonEncryptSql(mDataSource);
  }

  @Test
  public void addPersons()
  {
    List<Person> list = PersonCreate.create();
    for (Person value : list)
    {
      System.out.println(System.currentTimeMillis() + "|START");
      mSql.update("9M1})6Y]ibnxrp^zSQz@*BAc[Cn+Ub1R", value);
      System.out.println(System.currentTimeMillis() + "|END");
    }
  }

  // @Test
  public void test()
  {
    Person model = new Person();
    model.normalize();
    model.setCongId(1);
    model.setFsgId(3);
    model.setId(63L);
    model.setFirst("Jane");
    model.setLast("Roe");

    mSql.insert("1234567890", model);
  }

  // @Test
  public void select()
  {
    List<Person> model = mSql.selectById("1234567890", 63L);
    Assert.assertEquals(1, model.size());

    model = mSql.selectByCongId("1234567890", 1);
    Assert.assertEquals(1, model.size());

    // model = mSql.selectByFsgId("1234567890", 3);
    // Assert.assertEquals(2, model.size());
  }

  // @Test
  public void update()
  {
    List<Person> model = mSql.selectById("1234567890", 63L);

    Person p = model.get(0);
    p.setEmail("jane@gmail.com");

    Assert.assertEquals(1, mSql.update("1234567890", p));
  }
}