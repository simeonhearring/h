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
import static h.model.shared.khall.Part.SONG_2;
import static h.model.shared.khall.Part.SONG_3;
import static h.model.shared.khall.Part.S_RETURN_VISIT;
import static h.model.shared.khall.Part.TALK;
import static h.model.shared.khall.Part.TREASURES;
import static h.model.shared.khall.Part.T_RETURN_VISIT;
import static h.model.shared.util.RandomUtil.random;
import static h.model.shared.util.RandomUtil.randomInt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import h.model.shared.khall.Assignment;
import h.model.shared.khall.Curriculum;
import h.model.shared.khall.Hall;
import h.model.shared.khall.Part;
import h.model.shared.khall.Person;
import h.model.shared.khall.StudyPoint;

public class RandomAssignments
{
  private static Part[] PARTS =
  {
      CHAIRMAN, SONG_1, TREASURES, DIGGING, BIBLE_READING, LIVING_1, C_BIBLE_STUDY, SONG_3
  };

  private static PartSort[] PARTSA =
  {
      new PartSort(F_RETURN_VISIT, 8),
      new PartSort(TALK, 10),
      new PartSort(LIVING_2, getSortId(LIVING_2))
  };

  private static PartSort[] PARTSB =
  {
      new PartSort(INITIAL_CALL, 8), new PartSort(S_RETURN_VISIT, 9), new PartSort(BIBLE_STUDY, 10)
  };

  private static PartSort[] PARTSC =
  {
      new PartSort(T_RETURN_VISIT, 9),
      new PartSort(TALK, 10),
      new PartSort(LIVING_2, getSortId(LIVING_2))
  };

  private static PartSort[] PARTSD =
  {
      new PartSort(INITIAL_CALL, 8), new PartSort(BIBLE_STUDY, 10),
  };

  private static PartSort[] PARTSF =
  {
      new PartSort(SONG_2, getSortId(SONG_2)),
      new PartSort(INITIAL_CALL, 8),
      new PartSort(T_RETURN_VISIT, 9),
      new PartSort(BIBLE_STUDY, 10),
      new PartSort(LIVING_2, getSortId(LIVING_2))
  };

  private static PartSort[][] PARTSE =
  {
      PARTSA, PARTSB, PARTSC, PARTSD
  };

  public static final long MON_JAN_2_2017 = 1483336800000L;
  public static final long MON_JAN_1_2018 = 1514786400000L;
  public static final long MON_JAN_4_2016_19 = 1451955600000L;
  public static final long TUE_JAN_5_2016_19 = 1452042000000L;
  public static final long THU_JAN_7_2016_1930 = 1452216600000L;

  private static class PartSort
  {
    Part mPart;
    int mSort;
    // int mDuration;

    public PartSort(Part inPart, int inSort)
    {
      mPart = inPart;
      mSort = inSort;
    }

    // public PartSort(Part inPart, int inSort, int inDuration)
    // {
    // mPart = inPart;
    // mSort = inSort;
    // mDuration = inDuration;
    // }
  }

  public static List<Assignment> assignments(List<Person> inPersons, boolean inForceAssign)
  {
    List<Assignment> ret = new ArrayList<>();
    long date = MON_JAN_2_2017;
    for (int i = 0; i < 55; i++)
    {
      date += 604800 * 1000;
      assigns(ret, new Date(date), inPersons, inForceAssign);
    }
    return ret;
  }

  public static List<Assignment> assigns(Date inWeekOf, List<Person> inPersons,
      boolean inForceAssign)
  {
    List<Assignment> ret = new ArrayList<>();
    assigns(ret, inWeekOf, inPersons, inForceAssign);
    return ret;
  }

  public static void assigns(List<Assignment> ret, Date inWeekOf, List<Person> inPersons,
      boolean inForceAssign)
  {
    for (Part value : PARTS)
    {
      assign(ret, inWeekOf, inPersons, value, getSortId(value), inForceAssign);
    }

    if (inForceAssign)
    {
      for (PartSort value : PARTSF)
      {
        assign(ret, inWeekOf, inPersons, value.mPart, value.mSort, inForceAssign);
      }
    }
    else
    {
      for (PartSort value : random(PARTSE))
      {
        assign(ret, inWeekOf, inPersons, value.mPart, value.mSort, inForceAssign);
      }
    }
  }

  private static void assign(List<Assignment> inList, Date inWeekOf, List<Person> inPersons,
      Part inPart, int inSort, boolean inForceAssign)
  {
    inList.add(assignment(inWeekOf, inPart, Hall.MAIN, inSort, inPersons, inForceAssign));

    if (inPart.isStudyPoint())
    {
      inList.add(assignment(inWeekOf, inPart, Hall.SECOND, inSort, inPersons, inForceAssign));
    }
  }

  public static Assignment assignment(Date inWeekOf, Part inPart, Hall inHall,
      int inSort, List<Person> inPersons)
  {
    return assignment(inWeekOf, inPart, inHall, inSort, inPersons, false);
  }

  public static Assignment assignment(Date inWeekOf, Part inPart, Hall inHall,
      int inSort, List<Person> inPersons, boolean inForceAssign)
  {
    Curriculum curriculum = new Curriculum();
    curriculum.setDate(inWeekOf);
    curriculum.setPart(inPart);
    curriculum.setSort(inSort);
    curriculum.setTheme(theme(inPart));
    curriculum.setSource(source(inPart));
    curriculum.setDurationMinutes(inPart.gDuration());

    Assignment ret = new Assignment();
    ret.setCurriculum(curriculum);
    ret.setSchool(inHall);

    if (inPersons != null)
    {
      if (inForceAssign || randomInt(11) % 2 == 0)
      {
        ret.setParticipantId(random(inPersons).getIdLong());

        if (ret.getPart().isAssisted())
        {
          ret.setAssistantId(random(inPersons).getIdLong());
        }
        if (ret.getPart().isStudyPoint())
        {
          if (randomInt(11) % 2 == 0)
          {
            ret.setStudyPoint(random(StudyPoint.values()));
          }
        }
      }
    }
    return ret;
  }

  private static String source(Part inPart)
  {
    String ret = null;
    switch (inPart)
    {
      case SONG_1:
      case SONG_2:
      case SONG_3:
        ret = "77";
        break;
      case CHAIRMAN:
        ret = "JEREMIAH 51-52";
        break;
      default:
        break;
    }
    return ret;
  }

  private static String theme(Part inPart)
  {
    String ret = null;
    switch (inPart)
    {
      case TREASURES:
        ret = "“Jehovah’s Word Comes True in Every Detail”";
        break;
      case LIVING_2:
        ret = "Local Needs";
        break;
      case LIVING_1:
        ret = "“How Strong Is Your Faith in Jehovah’s Promises?”";
        break;
      default:
        break;
    }
    return ret;
  }

  public static int getSortId(Part inPart)
  {
    int ret = -1;
    switch (inPart)
    {
      case CHAIRMAN:
        ret = 1;
        break;
      case SONG_1:
        ret = 2;
        break;
      // OPENING COMMENTS = 3
      case TREASURES:
        ret = 4;
        break;
      case DIGGING:
        ret = 5;
        break;
      case BIBLE_READING:
        ret = 6;
        break;
      // PREPARE = 7
      case APPLY1:
        ret = 8;
      case APPLY2:
        ret = 9;
      case APPLY3:
        ret = 10;
      case SONG_2:
        ret = 11;
      case LIVING_1:
        ret = 12;
        break;
      case LIVING_2:
        ret = 13;
        break;
      case C_BIBLE_STUDY:
        ret = 14;
        break;
      // REVIEW = 15
      // CO_TALK = 16
      case SONG_3:
        ret = 17;
      default:
        break;
    }
    return ret;
  }
}