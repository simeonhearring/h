package h.json;

import org.junit.Assert;
import org.junit.Test;

import h.model.shared.khall.Person;

public class PersonJsonTest
{
  @Test
  public void test()
  {
    Person model = new Person();
    model.normalize();
    model.setCongId(1);
    model.setFsgId(3);
    model.setFirst("Jane");
    model.setLast("Roe");

    JsonMapper mapper = new JsonMapper();
    String jsonTxt = mapper.writeValue(model);

    Assert.assertNotNull(jsonTxt);

    Person results = mapper.readValue(jsonTxt, Person.class);
    Assert.assertEquals(model.getFirst(), results.getFirst());
    Assert.assertEquals(model.getLast(), results.getLast());
  }
}