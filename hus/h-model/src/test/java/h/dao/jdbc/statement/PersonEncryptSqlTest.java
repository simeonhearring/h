package h.dao.jdbc.statement;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import h.dao.jdbc.MySqlBaseDaoTest;
import h.json.PersonCreate;
import h.model.shared.khall.Part;
import h.model.shared.khall.Person;

public class PersonEncryptSqlTest extends MySqlBaseDaoTest
{
  private PersonEncryptSql mSql;

  @Before
  public void before()
  {
    mSql = new PersonEncryptSql(mDataSource);
  }

  // @Test
  public void addPersons()
  {
    List<Person> list = PersonCreate.create();
    for (Person value : list)
    {
      value.getParts().addAll(Part.parts(value));
      mSql.update("9M1})6Y]ibnxrp^zSQz@*BAc[Cn+Ub1R", value);
    }
  }

  @Test
  public void update2()
  {
    List<String> names = new ArrayList<>();
    names.add("Ackerman, Leslie");
    names.add("Clay, Ray");
    names.add("Daniel, Casey");
    names.add("Davis, Antonia");
    names.add("Ewing, Pauline");
    names.add("Ferrell, Michelle");
    names.add("Fleming, Linda");
    names.add("Giles, Teresa");
    names.add("Hatch, Branden");
    names.add("Hatch, Mikale");
    names.add("Heggie, Sue");
    names.add("Hooper, Ray");
    names.add("Hudson, Debi");
    names.add("Hudson, Mike");
    names.add("Jones, Bessie");
    names.add("Kemmeries, Janice");
    names.add("Kimmons, Tyler");
    names.add("Loewen, Marilyn");
    names.add("Martin, Dorris Fay");
    names.add("McClanahan, Quentin");
    names.add("Minor, Nadine");
    names.add("Nelson, Austin");
    names.add("Nimrod, Shadrach");
    names.add("Ogletree, Sandra");
    names.add("Ryan, Bill");
    names.add("Ryan, Pat");
    names.add("Saxton, Sherita");
    names.add("Scott, Crystal");
    names.add("Smith, Paul");
    names.add("Stewart, Bruce");
    names.add("Stewart, Patsy");
    names.add("Sutton, Belinda");
    names.add("Walker, Joe Jr");

    List<Person> list = mSql.selectByCongId("9M1})6Y]ibnxrp^zSQz@*BAc[Cn+Ub1R", 59);
    for (Person value : list)
    {
      if (names.contains(value.getName()))
      {
        value.getParts().clear();
        mSql.update("9M1})6Y]ibnxrp^zSQz@*BAc[Cn+Ub1R", value);
      }
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