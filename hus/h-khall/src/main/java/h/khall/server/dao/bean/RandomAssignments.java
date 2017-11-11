package h.khall.server.dao.bean;

import static h.khall.shared.model.Part.BIBLE_READING;
import static h.khall.shared.model.Part.BIBLE_STUDY;
import static h.khall.shared.model.Part.CHAIRMAN;
import static h.khall.shared.model.Part.C_BIBLE_STUDY;
import static h.khall.shared.model.Part.DIGGING;
import static h.khall.shared.model.Part.F_RETURN_VISIT;
import static h.khall.shared.model.Part.INITIAL_CALL;
import static h.khall.shared.model.Part.LIVING_1;
import static h.khall.shared.model.Part.LIVING_2;
import static h.khall.shared.model.Part.PRAYER_1;
import static h.khall.shared.model.Part.PRAYER_2;
import static h.khall.shared.model.Part.S_RETURN_VISIT;
import static h.khall.shared.model.Part.TALK;
import static h.khall.shared.model.Part.TREASURES;
import static h.khall.shared.model.Part.T_RETURN_VISIT;

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
  private static Part[] PARTS =
  {
      CHAIRMAN, PRAYER_1, TREASURES, DIGGING, BIBLE_READING, LIVING_1, C_BIBLE_STUDY, PRAYER_2
  };

  private static Part[] PARTSA =
  {
      F_RETURN_VISIT, TALK, LIVING_2
  };

  private static Part[] PARTSB =
  {
      INITIAL_CALL, S_RETURN_VISIT, BIBLE_STUDY,
  };

  private static Part[] PARTSC =
  {
      T_RETURN_VISIT, TALK, LIVING_2
  };

  private static Part[] PARTSD =
  {
      INITIAL_CALL, BIBLE_STUDY,
  };

  private static Part[][] PARTSE =
  {
      PARTSA, PARTSB, PARTSC, PARTSD
  };

  public static List<Assignment> assigns(List<Person> inPersons)
  {
    List<Assignment> ret = new ArrayList<>();
    long date = 1483336800000L;
    for (int i = 0; i < 10; i++)
    {
      date += 604800 * 1000;
      assigns(ret, new Date(date), inPersons);
    }
    return ret;
  }

  public static List<Assignment> assigns(Date inWeekOf, List<Person> inPersons)
  {
    List<Assignment> ret = new ArrayList<>();
    assigns(ret, inWeekOf, inPersons);
    return ret;
  }

  public static void assigns(List<Assignment> ret, Date inWeekOf, List<Person> inPersons)
  {
    for (Part value : PARTS)
    {
      assign(ret, inWeekOf, inPersons, value);
    }

    for (Part value : RandomUtil.random(PARTSE))
    {
      assign(ret, inWeekOf, inPersons, value);
    }
  }

  private static void assign(List<Assignment> inList, Date inWeekOf, List<Person> inPersons,
      Part inPart)
  {
    inList.add(assignment(inWeekOf, inPart, Hall.MAIN, inPersons));

    if (inPart.isStudyPoint())
    {
      inList.add(assignment(inWeekOf, inPart, Hall.AUX1, inPersons));
    }
  }

  private static Assignment assignment(Date inWeekOf, Part inPart, Hall inHall,
      List<Person> inPersons)
  {
    Assignment ret = new Assignment();
    ret.setWeekOf(inWeekOf);
    ret.setPart(inPart);
    ret.setHall(inHall);

    if (RandomUtil.randomInt(11) % 2 == 0)
    {
      ret.setParticipant(RandomUtil.random(inPersons));

      if (ret.getPart().isAssisted())
      {
        ret.setAssistant(RandomUtil.random(inPersons));
      }
      if (ret.getPart().isStudyPoint())
      {
        if (RandomUtil.randomInt(11) % 2 == 0)
        {
          ret.setStudyPoint(RandomUtil.random(StudyPoint.values()));
        }
      }
    }
    return ret;
  }
}