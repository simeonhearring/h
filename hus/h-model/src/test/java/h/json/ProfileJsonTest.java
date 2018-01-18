package h.json;

import org.junit.Assert;
import org.junit.Test;

import h.model.shared.Profile.Type;
import h.model.shared.khall.Assignments.Count;
import h.model.shared.khall.Profile;

public class ProfileJsonTest
{
  @Test
  public void test()
  {
    Profile model = new Profile();
    model.setCongNum("123456");
    model.setCongId(1);
    model.setCount(Count.NON_STUDENT);
    model.setFirst("First");
    model.setType(Type.USER);
    model.setUserId("User Id");
    model.setUserTitle("User title");
    model.setLast("Last");
    model.setPassword("password");
    model.setUserName("User name");
    model.setThreshold(5.0);

    model.setEncrypt("encrypt");
    model.setYear(2018);

    JsonMapper mapper = new JsonMapper();

    String jsonTxt = mapper.writeValue(model);
    System.out.println(jsonTxt);
    Assert.assertNotNull(jsonTxt);

    Profile results = mapper.readValue(jsonTxt, Profile.class);
    Assert.assertEquals(model.getFirst(), results.getFirst());
    Assert.assertEquals(model.getLast(), results.getLast());
  }
}