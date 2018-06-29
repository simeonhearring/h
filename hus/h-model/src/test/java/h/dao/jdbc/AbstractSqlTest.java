package h.dao.jdbc;

import org.junit.Test;

import h.model.shared.khall.Person;
import h.util.ResourceUtil;

public class AbstractSqlTest
{

  @Test
  public void test()
  {
    String text = ResourceUtil.contents("h/dao/jdbc/SamplePerson.json");
    System.out.println(text);
    AbstractSql sql = new AbstractSql()
    {
    };

    Person p = sql.readValue(text, Person.class);
    System.out.println(p);
    // p.setId(1L);
    System.out.println(sql.writeValue(p));

    Person p1 = sql.readValue(sql.writeValue(p), Person.class);
    System.out.println(p1);
  }
}