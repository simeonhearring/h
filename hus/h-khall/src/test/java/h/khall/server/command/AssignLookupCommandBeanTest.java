package h.khall.server.command;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import h.khall.shared.model.Part;
import h.model.shared.Tag;

public class AssignLookupCommandBeanTest
{
  private AssignLookupCommandBean mBean;

  @Before
  public void before()
  {
    mBean = new AssignLookupCommandBean();
  }

  @Test
  public void canFilterStudyPoint()
  {
    List<Tag> tags = new ArrayList<>();
    mBean.filterStudyPoint(tags, Part.BIBLE_READING, "#Accurate");
    Assert.assertEquals(1, tags.size());
  }

  @Test
  public void canDeterminStartsWith()
  {
    Assert.assertEquals(true, mBean.startsWith("#Accurate"));
    Assert.assertEquals(false, mBean.startsWith("Accurate"));
    Assert.assertEquals(true, mBean.startsWith("1 Accurate"));
    Assert.assertEquals(true, mBean.startsWith("2 Accurate"));
    Assert.assertEquals(true, mBean.startsWith("3 Accurate"));
    Assert.assertEquals(true, mBean.startsWith("4 Accurate"));
    Assert.assertEquals(true, mBean.startsWith("5 Accurate"));
    Assert.assertEquals(true, mBean.startsWith("6 Accurate"));
    Assert.assertEquals(true, mBean.startsWith("7 Accurate"));
    Assert.assertEquals(true, mBean.startsWith("8 Accurate"));
    Assert.assertEquals(true, mBean.startsWith("9 Accurate"));
  }
}
