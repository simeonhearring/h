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
import h.model.shared.khall.Persons;
import h.model.shared.khall.StudyPoint;

public class MeetingTest
{
  @SuppressWarnings("deprecation")
  @Test
  public void test1()
  {
    Meeting model = new Meeting();
    model.addAssignments(
        RandomAssignments.assigns(new Date(), RandomPersons.persons().getPersons(), false));
    Assert.assertEquals(1, model.gMonth(2017, new Date().getMonth()).size());
  }

  @Test
  public void test2()
  {
    Meeting model = meeting(false);

    Assert.assertEquals(true, model.gMonth(2017, 1).size() > 0);

    Assert.assertEquals(true, model.gWeek(2017, 2, 0).gAssignments().size() > 0);
    Assert.assertEquals(0, model.gWeek(2017, 2, 5).gAssignments().size());
  }

  @Test
  public void whenCountIsAllAllPartsAreIncluded()
  {
    Meeting model = new Meeting();
    model.setCount(Count.ALL);

    List<Assignment> assigns = new ArrayList<>();
    Date d = new Date(RandomAssignments.JAN_2_2017);
    assigns.add(RandomAssignments.assignment(d, Part.CHAIRMAN, Hall.MAIN, 9, null));
    assigns.add(RandomAssignments.assignment(d, Part.TREASURES, Hall.MAIN, 10, null));
    assigns.add(RandomAssignments.assignment(d, Part.F_RETURN_VISIT, Hall.MAIN, 11, null));
    assigns.add(RandomAssignments.assignment(d, Part.TALK, Hall.MAIN, 12, null));
    model.addAssignments(assigns);

    Assert.assertEquals(1, model.gMonth(2017, 0).size());

    Assert.assertEquals(4, model.gWeek(2017, 0, 0).gCount(), 0.0);
    Assert.assertEquals(0, model.gWeek(2017, 0, 0).gAssigned());

    assigns.get(0).setParticipantId(123L);
    assigns.get(0).setStudyPoint(StudyPoint.SP_1);

    Assert.assertEquals(4, model.gWeek(2017, 0, 0).gCount(), 0.0);
    Assert.assertEquals(1, model.gWeek(2017, 0, 0).gAssigned());

    Assert.assertEquals(4, model.gMonth(2017, 0).gCount(), 0.0);
    Assert.assertEquals(1, model.gMonth(2017, 0).gAssigned(), 0.0);

    Double[] expect1 = {4.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    Double[] expect2 = {1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    Assert.assertArrayEquals(expect1 , model.gYear(2017).gCountM());
    Assert.assertArrayEquals(expect2, model.gYear(2017).gAssignedM());
  }

  @Test
  public void whenCountIsStudentNonStudentPartsAreExcluded()
  {
    Meeting model = new Meeting();
    model.setCount(Count.STUDENT);

    List<Assignment> assigns = new ArrayList<>();
    Date d = new Date(RandomAssignments.JAN_2_2017);
    assigns.add(RandomAssignments.assignment(d, Part.CHAIRMAN, Hall.MAIN, 9, null));
    assigns.add(RandomAssignments.assignment(d, Part.TREASURES, Hall.MAIN, 10, null));
    assigns.add(RandomAssignments.assignment(d, Part.F_RETURN_VISIT, Hall.MAIN, 11, null));
    assigns.add(RandomAssignments.assignment(d, Part.TALK, Hall.MAIN, 12, null));
    model.addAssignments(assigns);

    Assert.assertEquals(1, model.gMonth(2017, 0).size());

    Assert.assertEquals(2, model.gWeek(2017, 0, 0).gCount(), 0.0);
    Assert.assertEquals(0, model.gWeek(2017, 0, 0).gAssigned());

    assigns.get(0).setParticipantId(123L);
    assigns.get(0).setStudyPoint(StudyPoint.SP_1);

    Assert.assertEquals(2, model.gWeek(2017, 0, 0).gCount(), 0.0);
    Assert.assertEquals(0, model.gWeek(2017, 0, 0).gAssigned());

    Assert.assertEquals(2, model.gMonth(2017, 0).gCount(), 0.0);
    Assert.assertEquals(0, model.gMonth(2017, 0).gAssigned(), 0.0);

    Double[] expect1 = {2.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    Double[] expect2 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    Assert.assertArrayEquals(expect1 , model.gYear(2017).gCountM());
    Assert.assertArrayEquals(expect2, model.gYear(2017).gAssignedM());
  }

  @Test
  public void canGetAssignmentHistory()
  {
    Meeting model = new Meeting();
    model.setCount(Count.STUDENT);

    List<Assignment> y2017 = new ArrayList<>();
    Date d2017 = new Date(RandomAssignments.JAN_2_2017);
    y2017.add(assignment(d2017, Part.CHAIRMAN, Hall.MAIN, 9, 1L));
    y2017.add(assignment(d2017, Part.TREASURES, Hall.MAIN, 10, 1L));
    y2017.add(assignment(d2017, Part.F_RETURN_VISIT, Hall.MAIN, 11, 1L));
    y2017.add(assignment(d2017, Part.TALK, Hall.MAIN, 12, 1L));
    model.addAssignments(y2017);

    List<Assignment> y2018 = new ArrayList<>();
    Date d2018 = new Date(RandomAssignments.JAN_1_2018);
    y2018.add(assignment(d2018, Part.CHAIRMAN, Hall.MAIN, 9, 1L));
    y2018.add(assignment(d2018, Part.TREASURES, Hall.MAIN, 10, 1L));
    y2018.add(assignment(d2018, Part.F_RETURN_VISIT, Hall.MAIN, 11, 1L));
    y2018.add(assignment(d2018, Part.TALK, Hall.MAIN, 12, 1L));
    model.addAssignments(y2018);

    List<Assignment> history = model.gHistory(1L);
    Assert.assertEquals(8, history.size());
  }

  private static Assignment assignment(Date inWeekOf, Part inPart, Hall inHall, int inSort,
      long inPersonId)
  {
    Assignment ret = RandomAssignments.assignment(inWeekOf, inPart, inHall, inSort, null);
    ret.setParticipantId(inPersonId);
    return ret;
  }

  public static Meeting meeting(boolean inForceAssign)
  {
    Meeting ret = new Meeting();
    Persons persons = RandomPersons.persons();
    ret.addAssignments(RandomAssignments.assignments(persons.getPersons(), inForceAssign));
    return ret;
  }
}