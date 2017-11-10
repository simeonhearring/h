package h.khall.server.dao.bean;

import java.util.ArrayList;
import java.util.List;

import h.model.shared.Person;
import h.tool.util.RandomUtil;

public class RandomPeople
{
  private static final String[] LAST =
  {
      "Smith", "Doe", "Roe", "Henderson", "Johnson", "Walker", "Davidson", "Kent", "Parker", "Wayne"
  };
  private static final String[] FIRST =
  {
      "John", "Jane", "Paul", "Phil", "Ron", "Don", "Pete", "Josh", "Simone", "Jackie", "Bruce"
  };

  public static List<Person> persons()
  {
    List<Person> ret = new ArrayList<>();
    for (int i = 0; i < 50; i++)
    {
      Person person = new Person();
      person.setLast(RandomUtil.random(LAST));
      person.setFirst(RandomUtil.random(FIRST));
      ret.add(person);
    }
    return ret;
  }
}