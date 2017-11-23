package h.khall.shared.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import h.khall.server.dao.bean.RandomAssignments;
import h.khall.server.dao.bean.RandomPersons;
import h.model.shared.khall.Assignment;
import h.model.shared.khall.Assignments.Count;
import h.model.shared.khall.Hall;
import h.model.shared.khall.Meeting;
import h.model.shared.khall.Part;
import h.model.shared.khall.StudyPoint;

public class MeetingTest
{
  @SuppressWarnings("deprecation")
  @Test
  public void test1()
  {
    Meeting model = new Meeting();
    model.setAssignments(
        RandomAssignments.assigns(new Date(), RandomPersons.persons().getPersons()));
    Assert.assertEquals(1, model.getMonth(2017, new Date().getMonth()).size());
  }

  @Test
  public void test2()
  {
    Meeting model = new Meeting();
    List<Assignment> assigns = RandomAssignments.assigns(RandomPersons.persons().getPersons());
    model.setAssignments(assigns);
    Assert.assertEquals(true, model.getMonth(2017, 1).size() > 0);

    Assert.assertEquals(true, model.getWeek(2017, 2, 0).gAssignment().size() > 0);
    Assert.assertEquals(0, model.getWeek(2017, 2, 5).gAssignment().size());
  }

  @Test
  public void test3()
  {
    Meeting model = new Meeting();

    List<Assignment> assigns = new ArrayList<>();
    Date d = new Date(RandomAssignments.JAN_2_2017);
    assigns.add(RandomAssignments.assignment(d, Part.CHAIRMAN, Hall.MAIN, 9, null));
    assigns.add(RandomAssignments.assignment(d, Part.TREASURES, Hall.MAIN, 10, null));
    assigns.add(RandomAssignments.assignment(d, Part.F_RETURN_VISIT, Hall.MAIN, 11, null));
    assigns.add(RandomAssignments.assignment(d, Part.TALK, Hall.MAIN, 12, null));
    model.setAssignments(assigns);

    Assert.assertEquals(1, model.getMonth(2017, 0).size());

    Assert.assertEquals(4, model.getWeek(2017, 0, 0).gCount(), 0.0);
    Assert.assertEquals(0, model.getWeek(2017, 0, 0).gAssigned());

    assigns.get(0).setParticipantId(123L);
    assigns.get(0).setStudyPoint(StudyPoint.SP_1);

    Assert.assertEquals(4, model.getWeek(2017, 0, 0).gCount(), 0.0);
    Assert.assertEquals(1, model.getWeek(2017, 0, 0).gAssigned());

    Assert.assertEquals(4, model.getMonth(2017, 0).gCount(), 0.0);
    Assert.assertEquals(1, model.getMonth(2017, 0).gAssigned(), 0.0);

    Double[] expect1 = {4.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    Double[] expect2 = {1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    Assert.assertArrayEquals(expect1 , model.getYear(2017).gCountM());
    Assert.assertArrayEquals(expect2, model.getYear(2017).gAssignedM());
  }

  public void test4()
  {
    Meeting model = new Meeting();
    model.setCount(Count.STUDENT);

    List<Assignment> assigns = new ArrayList<>();
    Date d = new Date(RandomAssignments.JAN_2_2017);
    assigns.add(RandomAssignments.assignment(d, Part.CHAIRMAN, Hall.MAIN, 9, null));
    assigns.add(RandomAssignments.assignment(d, Part.TREASURES, Hall.MAIN, 10, null));
    assigns.add(RandomAssignments.assignment(d, Part.F_RETURN_VISIT, Hall.MAIN, 11, null));
    assigns.add(RandomAssignments.assignment(d, Part.TALK, Hall.MAIN, 12, null));
    model.setAssignments(assigns);

    Assert.assertEquals(1, model.getMonth(2017, 0).size());

    Assert.assertEquals(2, model.getWeek(2017, 0, 0).gCount(), 0.0);
    Assert.assertEquals(0, model.getWeek(2017, 0, 0).gAssigned());

    assigns.get(0).setParticipantId(123L);
    assigns.get(0).setStudyPoint(StudyPoint.SP_1);

    Assert.assertEquals(2, model.getWeek(2017, 0, 0).gCount(), 0.0);
    Assert.assertEquals(0, model.getWeek(2017, 0, 0).gAssigned());

    Assert.assertEquals(2, model.getMonth(2017, 0).gCount(), 0.0);
    Assert.assertEquals(0, model.getMonth(2017, 0).gAssigned(), 0.0);

    Double[] expect1 = {2.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    Double[] expect2 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    Assert.assertArrayEquals(expect1 , model.getYear(2017).gCountM());
    Assert.assertArrayEquals(expect2, model.getYear(2017).gAssignedM());
  }
}