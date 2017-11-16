package h.khall.shared.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import h.model.shared.Tag;
import h.model.shared.khall.Part;
import h.model.shared.khall.StudyPoint;

public class StudyPointTest
{
  @Test
  public void canFilterStudyPoint()
  {
    List<Tag> tags = new ArrayList<>();
    StudyPoint.filter(tags, Part.BIBLE_READING, "#Accurate");
    Assert.assertEquals(1, tags.size());
  }

  @Test
  public void canDeterminStartsWith()
  {
    Assert.assertEquals(true, StudyPoint.doFilter("#Accurate"));
    Assert.assertEquals(false, StudyPoint.doFilter("Accurate"));
    Assert.assertEquals(true, StudyPoint.doFilter("1 Accurate"));
    Assert.assertEquals(true, StudyPoint.doFilter("2 Accurate"));
    Assert.assertEquals(true, StudyPoint.doFilter("3 Accurate"));
    Assert.assertEquals(true, StudyPoint.doFilter("4 Accurate"));
    Assert.assertEquals(true, StudyPoint.doFilter("5 Accurate"));
    Assert.assertEquals(true, StudyPoint.doFilter("6 Accurate"));
    Assert.assertEquals(true, StudyPoint.doFilter("7 Accurate"));
    Assert.assertEquals(true, StudyPoint.doFilter("8 Accurate"));
    Assert.assertEquals(true, StudyPoint.doFilter("9 Accurate"));
  }
}