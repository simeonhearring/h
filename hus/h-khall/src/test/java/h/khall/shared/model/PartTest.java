package h.khall.shared.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PartTest
{
  @Test
  public void test()
  {
    List<Part> parts = new ArrayList<>();

    Assert.assertEquals(Part.F_RETURN_VISIT, Part.F_RETURN_VISIT.match(parts));

    parts.add(Part.BIBLE_STUDY);
    parts.add(Part.S_RETURN_VISIT);

    Assert.assertEquals(Part.S_RETURN_VISIT, Part.F_RETURN_VISIT.match(parts));
    Assert.assertEquals(Part.BIBLE_STUDY, Part.TALK.match(parts));
  }
}