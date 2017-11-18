package h.khall.server.dao.bean;

import static h.model.shared.khall.Part.BIBLE_READING;
import static h.model.shared.khall.Part.BIBLE_STUDY;
import static h.model.shared.khall.Part.CHAIRMAN;
import static h.model.shared.khall.Part.C_BIBLE_STUDY;
import static h.model.shared.khall.Part.DIGGING;
import static h.model.shared.khall.Part.F_RETURN_VISIT;
import static h.model.shared.khall.Part.INITIAL_CALL;
import static h.model.shared.khall.Part.LIVING_1;
import static h.model.shared.khall.Part.LIVING_2;
import static h.model.shared.khall.Part.SONG_1;
import static h.model.shared.khall.Part.SONG_3;
import static h.model.shared.khall.Part.S_RETURN_VISIT;
import static h.model.shared.khall.Part.TALK;
import static h.model.shared.khall.Part.TREASURES;
import static h.model.shared.khall.Part.T_RETURN_VISIT;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import h.model.shared.khall.Assignment;
import h.model.shared.khall.Curriculum;
import h.model.shared.khall.Hall;
import h.model.shared.khall.Part;
import h.model.shared.khall.Person;
import h.model.shared.khall.StudyPoint;
import h.tool.util.RandomUtil;

public class RandomAssignments
{
  private static Part[] PARTS =
  {
      CHAIRMAN, SONG_1, TREASURES, DIGGING, BIBLE_READING, LIVING_1, C_BIBLE_STUDY, SONG_3
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
    long date = 1483336800000L; // January 2, 2017
    for (int i = 0; i < 55; i++)
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
      inList.add(assignment(inWeekOf, inPart, Hall.SECOND, inPersons));
    }
  }

  private static Assignment assignment(Date inWeekOf, Part inPart, Hall inHall,
      List<Person> inPersons)
  {
    Curriculum curriculum = new Curriculum();
    curriculum.setDate(inWeekOf);
    curriculum.setPart(inPart);

    Assignment ret = new Assignment();
    ret.setCurriculum(curriculum);
    ret.setSchool(inHall);

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