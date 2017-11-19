package h.json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import h.model.shared.khall.Curriculum;
import h.model.shared.khall.Curriculums;
import h.model.shared.khall.Part;

public class CurriculumJsonTest
{
  @Test
  public void test()
  {
    Curriculums curr = new Curriculums();
    curr.setCurriculums(CurriculumCreate.create());

    JsonMapper json = new JsonMapper();
    String text = json.writeValue(curr);
    System.out.println(text);

    Curriculum model = new Curriculum();
    model.setDate(newDate("2018-01-01"));
    model.setPart(Part.CHAIRMAN);
    model.setTheme(null);
    model.setSource("MATTHEW 1-3");
    model.setDurationMinutes(null);
    model.setSort(1);

    String j = json.writeValue(model);

    Assert.assertNotNull(j);

    Curriculum results = json.readValue(j, Curriculum.class);

    Assert.assertEquals(model.getId(), results.getId());
    Assert.assertEquals(model.getDate(), results.getDate());
    Assert.assertEquals(model.getDurationMinutes(), results.getDurationMinutes());
    Assert.assertEquals(model.getPart(), results.getPart());
    Assert.assertEquals(model.getSort(), results.getSort());
    Assert.assertEquals(model.getSource(), results.getSource());
    Assert.assertEquals(model.getTheme(), results.getTheme());
  }

  private Date newDate(String inText)
  {
    try
    {
      return new SimpleDateFormat("yyyy-MM-dd").parse(inText);
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    return null;
  }
}