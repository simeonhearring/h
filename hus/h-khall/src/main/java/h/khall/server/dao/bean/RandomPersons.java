package h.khall.server.dao.bean;

import static h.model.shared.util.RandomUtil.random;
import static h.model.shared.util.RandomUtil.randomNumbers;

import java.util.ArrayList;
import java.util.List;

import h.model.shared.Person.Gender;
import h.model.shared.khall.Person;
import h.model.shared.khall.Persons;
import h.model.shared.khall.Roles;
import h.model.shared.khall.Roles.Role;

public class RandomPersons
{
  public static Persons persons()
  {
    int[] fsg =
    {
        1, 2, 3, 4, 5
    };
    Persons ret = new Persons();
    List<Person> list = new ArrayList<>();
    for (int i = 0; i < 150; i++)
    {
      Person person = person(i, fsg);

      list.add(person);
    }
    ret.setPersons(list);
    return ret;
  }

  public static Person person(long inId, int[] inFsg)
  {
    Person person = new Person();
    person.setId(Long.valueOf(inId));
    person.setLast(random(LAST));
    person.setFirst(random(FIRST));
    person.setEmail(person.getName().replaceAll(",", "").replaceAll(" ", "_").toLowerCase() + "@gmail.com");
    person.setMobile(randomNumbers(3) + "-" + randomNumbers(3) + "-" + randomNumbers(4));
    person.setHome(randomNumbers(3) + "-" + randomNumbers(3) + "-" + randomNumbers(4));
    person.setGender(random(Gender.values()));
    person.setAddress1(randomNumbers(random(2, 3, 4, 5)) + random(STREET));
    String[] city = random(CITY);
    person.setCity(city[0]);
    person.setState(city[1]);
    person.setZip(String.valueOf(randomNumbers(5)));

    if (inFsg != null)
    {
      person.setFsgId(random(inFsg));
    }
    person.setRoles(roles());

    person.normalize();
    return person;
  }

  private static Roles roles()
  {
    Roles ret = new Roles();
    ret.normalize();
    if (random(BOOL))
    {
      ret.add(Role.STUDENT);
    }
    if (random(BOOL) && random(BOOL) && random(BOOL))
    {
      ret.add(Role.ELDER);
    }
    if (random(BOOL) && random(BOOL))
    {
      ret.add(Role.REGULAR_PIONEER);
    }
    return ret;
  }

  private static final Boolean[] BOOL =
  {
      true, false
  };
  private static final String[] LAST =
  {
      "Smith", "Doe", "Roe", "Henderson", "Johnson", "Walker", "Davidson", "Kent", "Parker", "Wayne"
  };
  private static final String[] FIRST =
  {
      "John", "Jane", "Paul", "Phil", "Ron", "Don", "Pete", "Josh", "Simone", "Jackie", "Bruce"
  };
  private static final String[] STREET =
  {
      " Arthington St.",
      " Main St.",
      " E 8th.",
      " Washtington Blvd.",
      " Alvinwood Ct.",
      " 23rd St.",
      " 31st St.",
      " 79th Rd"
  };
  private static final String[][] CITY =
  {
      {
          "Chicago", "IL"
      },
      {
          "Nashville", "TN"
      },
      {
          "Springfield", "IL"
      },
      {
          "Gallatin", "TN"
      },
      {
          "Maywood", "IL"
      }
  };
}