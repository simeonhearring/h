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
  public void test()
  {
    Meeting model = new Meeting();
    model.setAssignments(RandomAssignments.assigns(new Date(), RandomPeople.persons()));
    Assert.assertEquals(1, model.getMonth(2017, new Date().getMonth()).size());
  }
}