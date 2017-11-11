package h.khall.shared.model;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import h.khall.server.dao.bean.RandomAssignments;
import h.khall.server.dao.bean.RandomPeople;

public class MeetingTest
{
  @SuppressWarnings("deprecation")
  @Test
  public void test1()
  {
    Meeting model = new Meeting();
    model.setAssignments(RandomAssignments.assigns(new Date(), RandomPeople.persons()));
    Assert.assertEquals(1, model.getMonth(2017, new Date().getMonth()).size());
  }

  @Test
  public void test2()
  {
    Meeting model = new Meeting();
    model.setAssignments(RandomAssignments.assigns(RandomPeople.persons()));
    Assert.assertEquals(true, model.getMonth(2017, 1).size() > 0);

    Assert.assertEquals(true, model.getWeek(2017, 2, 0).getAssignment().size() > 0);
    Assert.assertEquals(0, model.getWeek(2017, 2, 5).getAssignment().size());
  }
}