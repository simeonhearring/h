package h.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import h.model.shared.Person.Gender;
import h.model.shared.khall.Categories;
import h.model.shared.khall.Part;
import h.model.shared.khall.Person;
import h.model.shared.khall.Roles;

public class PersonJsonTest
{
  @Test
  public void test()
  {
    List<Person> ret = new ArrayList<>();

    Person model = new Person();
    model.normalize();

    model.setCongId(1);
    model.setFsgId(3);
    model.setFirst("");
    model.setLast("");
    model.setMiddle("");
    model.setSuffix("");
    model.setAddress(address(""));
    model.setCategories(newCat(""));
    model.setChildren("");
    model.setEmail("");
    model.setEmergency("");
    model.setFamily("");
    model.setHead(1L);
    model.setHome("");
    model.setId(1L);
    model.setMobile("");
    model.setGender(Gender.Female);
    model.setBaptized(newDate(""));
    model.setBirth(new Date(1L));
    model.setPublishing(newDate(""));
    model.setRoles(role(""));

    ret.add(model);

    model.setParts(Part.elder());

    JsonMapper mapper = new JsonMapper();


    String jsonTxt = mapper.writeValue(model);
    System.out.println(jsonTxt);
    Assert.assertNotNull(jsonTxt);

    Person results = mapper.readValue(jsonTxt, Person.class);
    Assert.assertEquals(model.getFirst(), results.getFirst());
    Assert.assertEquals(model.getLast(), results.getLast());
  }

  private String[] address(String inString)
  {
    // TODO Auto-generated method stub
    return null;
  }

  private Roles role(String inString)
  {
    // TODO Auto-generated method stub
    return null;
  }

  private Categories newCat(String inString)
  {
    // TODO Auto-generated method stub
    return null;
  }

  private Date newDate(String inString)
  {
    // TODO Auto-generated method stub
    return null;
  }
}