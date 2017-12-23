package h.model.shared.khall;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PersonsTest
{
  @Test
  public void test()
  {
    List<Person> list = new ArrayList<>();

    Person p1 = new Person();
    p1.setFsgId(1);
    p1.setFirst("Simeon");
    p1.setLast("Hearring");
    p1.normalize();
    list.add(p1);

    Person p2 = new Person();
    p2.setFsgId(0);
    p2.setFirst("Nadia");
    p2.setLast("Hearring");
    p2.normalize();
    list.add(p2);

    Person p3 = new Person();
    p3.setFsgId(0);
    p3.setFirst("Jathen");
    p3.setLast("Hearring");
    p3.normalize();
    list.add(p3);

    Assert.assertEquals(3, Persons.filter(list, "hear", false).size());
    Assert.assertEquals(1, Persons.filter(list, "hear", true).size());
  }
}