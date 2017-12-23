package h.model.shared.khall;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import h.json.CurriculumCreate;
import h.model.shared.Person.Gender;
import h.model.shared.khall.PartInfo.Info;

public class PartInfoTest
{
  @Test
  public void test()
  {
    Person person = new Person();
    person.setId(1L);
    person.setGender(Gender.Male);

    List<Assignment> assignments = assignments(person, 5, 6, 7);

    @SuppressWarnings("serial")
    Info model = new Info(person, assignments)
    {
      @Override
      protected Date gToday()
      {
        return new Date(1519884000000L);
      }
    };

    int[] results = model.score(Part.BIBLE_READING, null);

    Assert.assertEquals(0, results[0]);
    Assert.assertEquals(12, results[1]);
    Assert.assertEquals(5, results[2]);
    Assert.assertEquals(0, results[3]);
    Assert.assertEquals(17, model.getScore());
  }

  private List<Assignment> assignments(Person inPerson, int... inIs)
  {
    List<Assignment> ret = new ArrayList<>();

    List<Curriculum> c = CurriculumCreate.create();

    for (int pos : inIs)
    {
      Assignment a1 = new Assignment();
      a1.setCurriculum(c.get(pos));
      a1.setParticipantId(inPerson.getIdLong());
      ret.add(a1);
    }

    return ret;
  }

}
