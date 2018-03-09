package h.dao.jdbc.statement;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import h.dao.jdbc.MySqlBaseDaoTest;
import h.model.shared.khall.Person;

public class PersonEncryptSqlTest extends MySqlBaseDaoTest
{
  private PersonEncryptSql mNewSql;

  @Before
  public void before()
  {
    mNewSql = new PersonEncryptSql(mDataSource);
  }

  @Test
  public void updatePersonLocater()
  {
    List<Person> p = mNewSql.selectByCongId("NBgh(epoT(MSkV77kFaCE~cC_SSf7c7o", 60);
    int i = 1;
    for (Person value : p)
    {
      value.setCongId(60);
      if (value.getFsgId() == null)
      {
        value.setFsgId(0);
      }
      System.out.println(i++ + ")" + value.gName());
      mNewSql.update("NBgh(epoT(MSkV77kFaCE~cC_SSf7c7o", value);
    }
  }

  // @Test
  // public void addPersons()
  // {
  // List<Person> list = PersonCreate.create();
  // for (Person value : list)
  // {
  // value.getParts().addAll(Part.parts(value));
  // mNewSql.update("9M1})6Y]ibnxrp^zSQz@*BAc[Cn+Ub1R", value);
  // }
  // }

  // @Test
  // public void update2()
  // {
  // List<String> names = new ArrayList<>();
  // names.add("Ackerman, Leslie");
  // names.add("Clay, Ray");
  // names.add("Daniel, Casey");
  // names.add("Davis, Antonia");
  // names.add("Ewing, Pauline");
  // names.add("Ferrell, Michelle");
  // names.add("Fleming, Linda");
  // names.add("Giles, Teresa");
  // names.add("Hatch, Branden");
  // names.add("Hatch, Mikale");
  // names.add("Heggie, Sue");
  // names.add("Hooper, Ray");
  // names.add("Hudson, Debi");
  // names.add("Hudson, Mike");
  // names.add("Jones, Bessie");
  // names.add("Kemmeries, Janice");
  // names.add("Kimmons, Tyler");
  // names.add("Loewen, Marilyn");
  // names.add("Martin, Dorris Fay");
  // names.add("McClanahan, Quentin");
  // names.add("Minor, Nadine");
  // names.add("Nelson, Austin");
  // names.add("Nimrod, Shadrach");
  // names.add("Ogletree, Sandra");
  // names.add("Ryan, Bill");
  // names.add("Ryan, Pat");
  // names.add("Saxton, Sherita");
  // names.add("Scott, Crystal");
  // names.add("Smith, Paul");
  // names.add("Stewart, Bruce");
  // names.add("Stewart, Patsy");
  // names.add("Sutton, Belinda");
  // names.add("Walker, Joe Jr");
  //
  // List<Person> list =
  // mNewSql.selectByCongId("9M1})6Y]ibnxrp^zSQz@*BAc[Cn+Ub1R", 59);
  // for (Person value : list)
  // {
  // if (names.contains(value.getName()))
  // {
  // value.getParts().clear();
  // mNewSql.update("9M1})6Y]ibnxrp^zSQz@*BAc[Cn+Ub1R", value);
  // }
  // }
  // }

  // @Test
  // public void test()
  // {
  // Person model = new Person();
  // model.normalize();
  // model.setCongId(1);
  // model.setFsgId(3);
  // model.setId(63L);
  // model.setFirst("Jane");
  // model.setLast("Roe");
  //
  // mNewSql.insert("1234567890", model);
  // }

  // @Test
  // public void addTripleCreek()
  // {
  // Map<Integer, Integer> map = new HashMap<>();
  // map.put(79, 86);
  // map.put(80, 88);
  // map.put(82, 87);
  // map.put(83, 85);
  // map.put(0, 0);
  //
  // List<Person> model =
  // mNewSql.selectByCongId("9M1})6Y]ibnxrp^zSQz@*BAc[Cn+Ub1R", 59);
  // for (Person value : model)
  // {
  // if (map.containsKey(value.getFsgId()))
  // {
  // System.out.println(value.gNameAge());
  //
  // // value.setCongId(60);
  // // value.setFsgId(0);
  //
  // // mSql.update("9M1})6Y]ibnxrp^zSQz@*BAc[Cn+Ub1R", value);
  // // mSql.insert("NBgh(epoT(MSkV77kFaCE~cC_SSf7c7o", value);
  // }
  // }
  // }

  // @Test
  // public void select()
  // {
  // Person model = mNewSql.selectById("1234567890", 63L);
  // Assert.assertEquals(true, model != null);
  //
  // List<Person> models = mNewSql.selectByCongId("1234567890", 1);
  // Assert.assertEquals(1, models.size());
  //
  // // model = mSql.selectByFsgId("1234567890", 3);
  // // Assert.assertEquals(2, model.size());
  // }

  // @Test
  // public void selectCong()
  // {
  // List<Person> model =
  // mNewSql.selectByCongId("NBgh(epoT(MSkV77kFaCE~cC_SSf7c7o", 60);
  //
  // int i = 1;
  // for (Person value : model)
  // {
  // System.out.println(i++ + ") " + value.getId() + " " + value.getName());
  //
  // value.normalize();
  // value.getRoles().add(Role.PUBLISHER);
  //
  // mNewSql.update("NBgh(epoT(MSkV77kFaCE~cC_SSf7c7o", value);
  // }
  // }

  // @Test
  // public void update()
  // {
  // Person p = mNewSql.selectById("1234567890", 63L);
  // p.setEmail("jane@gmail.com");
  //
  // Assert.assertEquals(1, mNewSql.update("1234567890", p));
  // }
}