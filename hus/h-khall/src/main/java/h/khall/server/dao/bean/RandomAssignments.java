package h.khall.server.dao.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import h.khall.shared.model.Assignment;
import h.khall.shared.model.Hall;
import h.khall.shared.model.Part;
import h.khall.shared.model.StudyPoint;
import h.model.shared.Person;
import h.tool.util.RandomUtil;

public class RandomAssignments
{
  public static List<Assignment> assigns(Date inWeekOf, List<Person> inPersons)
  {
    List<Assignment> ret = new ArrayList<>();

    for (Part value : Part.values())
    {
      ret.add(assignment(inWeekOf, value, Hall.MAIN, inPersons));

      if (value.isStudyPoint())
      {
        ret.add(assignment(inWeekOf, value, Hall.AUX1, inPersons));
      }
    }

    return ret;
  }

  private static Assignment assignment(Date inWeekOf, Part inPart, Hall inHall,
      List<Person> inPersons)
  {
    Assignment ret = new Assignment();

    ret.setWeekOf(inWeekOf);
    ret.setPart(inPart);
    ret.setHall(inHall);
    ret.setParticipant(RandomUtil.random(inPersons));

    if (ret.getPart().isAssisted())
    {
      ret.setAssistant(RandomUtil.random(inPersons));
    }
    if (ret.getPart().isStudyPoint())
    {
      ret.setStudyPoint(RandomUtil.random(StudyPoint.values()));
    }
    return ret;
  }
}