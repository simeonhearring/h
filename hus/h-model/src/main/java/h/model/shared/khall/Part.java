package h.model.shared.khall;

import java.util.ArrayList;
import java.util.List;

import h.model.shared.util.StringUtil;

public enum Part implements HasGLabel
{
  CO_TALK,
  EVENT,
  OPEN,
  SONG_2,
  REVIEW,
  READER,
  RETURN_VISIT,
  ASSISTANT,
  APPLY1,
  APPLY2,
  APPLY3,
  APPLY4,
  APPLY_VIDEO,
  I_VIDEO,
  F_RETURN_VISIT_VIDEO,
  S_RETURN_VISIT_VIDEO,
  T_RETURN_VISIT_VIDEO,
  B_VIDEO,

  CHAIRMAN,
  SONG_1,
  TREASURES,
  DIGGING,
  BIBLE_READING,
  INITIAL_CALL,
  F_RETURN_VISIT,
  S_RETURN_VISIT,
  T_RETURN_VISIT,
  TALK,
  BIBLE_STUDY,
  LIVING_1,
  LIVING_2,
  C_BIBLE_STUDY,
  SONG_3;

  public static Part[] assignable()
  {
    return new Part[]
    {
        CHAIRMAN,
        TREASURES,
        DIGGING,
        SONG_1,
        SONG_3, // TODO replace with PRAYER

        BIBLE_READING,
        INITIAL_CALL,
        F_RETURN_VISIT,
        S_RETURN_VISIT,
        T_RETURN_VISIT,
        BIBLE_STUDY,
        ASSISTANT,
        TALK,

        LIVING_1,
        LIVING_2,
        C_BIBLE_STUDY,
        READER,
    };
  }

  public Part getParent()
  {
    Part ret = this;
    switch (this)
    {
      case S_RETURN_VISIT:
      case T_RETURN_VISIT:
        ret = F_RETURN_VISIT;
        break;
      case BIBLE_STUDY:
        ret = TALK;
        break;
      default:
        break;
    }
    return ret;
  }

  public boolean isChairmanPart()
  {
    return CHAIRMAN.equals(this) || OPEN.equals(this) || SONG_2.equals(this) || I_VIDEO.equals(this) || F_RETURN_VISIT_VIDEO.equals(this) || S_RETURN_VISIT_VIDEO.equals(this)
        || T_RETURN_VISIT_VIDEO.equals(this) || B_VIDEO.equals(this) || REVIEW.equals(this) || APPLY_VIDEO.equals(this);
  }

  public boolean isSelfThemed()
  {
    return DIGGING.equals(this) || BIBLE_READING.equals(this) || INITIAL_CALL.equals(this) || F_RETURN_VISIT.equals(this) || S_RETURN_VISIT.equals(this) || T_RETURN_VISIT.equals(this)
        || TALK.equals(this) || BIBLE_STUDY.equals(this) || I_VIDEO.equals(this) || F_RETURN_VISIT_VIDEO.equals(this) || S_RETURN_VISIT_VIDEO.equals(this) || T_RETURN_VISIT_VIDEO.equals(this)
        || B_VIDEO.equals(this) || C_BIBLE_STUDY.equals(this) || REVIEW.equals(this) || APPLY_VIDEO.equals(this);
  }

  public boolean isSong()
  {
    return SONG_1.equals(this) || SONG_2.equals(this) || SONG_3.equals(this);
  }

  public boolean isPrayer()
  {
    return SONG_1.equals(this) || SONG_3.equals(this);
  }

  public boolean isOrless()
  {
    return isStudyPoint() || OPEN.equals(this) || REVIEW.equals(this);
  }

  public static Part[] student()
  {
    return new Part[]
    {
        BIBLE_READING, INITIAL_CALL, F_RETURN_VISIT, S_RETURN_VISIT, T_RETURN_VISIT, TALK, BIBLE_STUDY
    };
  }

  @Override
  public String gLabel()
  {
    return getLabel(true);
  }

  public static Part[] nonstudent()
  {
    return new Part[]
    {
        CHAIRMAN, SONG_1, TREASURES, DIGGING, LIVING_1, LIVING_2, C_BIBLE_STUDY, SONG_3
    };
  }

  public static String labels(boolean inShort, String inDelim, Part... inParts)
  {
    StringBuilder sb = new StringBuilder();
    for (Part value : inParts)
    {
      sb.append(value.getLabel(inShort)).append(inDelim);
    }
    return sb.toString();
  }

  public boolean isAssisted()
  {
    return getParticipants() > 1;
  }

  public int getParticipants()
  {
    int ret = 1;
    switch (this)
    {
      case C_BIBLE_STUDY:
      case RETURN_VISIT:
      case INITIAL_CALL:
      case F_RETURN_VISIT:
      case S_RETURN_VISIT:
      case T_RETURN_VISIT:
      case BIBLE_STUDY:
        ret = 2;
        break;
      default:
        break;
    }
    return ret;
  }

  public boolean isStudyPoint()
  {
    boolean ret = false;
    switch (this)
    {
      case BIBLE_READING:
      case INITIAL_CALL:
      case F_RETURN_VISIT:
      case S_RETURN_VISIT:
      case T_RETURN_VISIT:
      case TALK:
      case BIBLE_STUDY:
        ret = true;
        break;
      default:
        break;
    }
    return ret;
  }

  public boolean isApply()
  {
    boolean ret = false;
    switch (this)
    {
      case APPLY1:
      case APPLY2:
      case APPLY3:
      case APPLY4:
        ret = true;
        break;
      default:
        break;
    }
    return ret;
  }

  public boolean isStudent()
  {
    return isStudyPoint();
  }

  public boolean isCoTalk()
  {
    return CO_TALK.equals(this);
  }

  public boolean isVideo()
  {
    boolean ret = false;
    switch (this)
    {
      case APPLY_VIDEO:
      case I_VIDEO:
      case F_RETURN_VISIT_VIDEO:
      case S_RETURN_VISIT_VIDEO:
      case T_RETURN_VISIT_VIDEO:
      case B_VIDEO:
        ret = true;
        break;
      default:
        break;
    }
    return ret;
  }

  public String getLabel(boolean inShort)
  {
    String ret = null;
    switch (this)
    {
      case SONG_1:
        ret = "Song and prayer";
        break;
      case SONG_3:
        ret = "Song and prayer";
        break;
      case TREASURES:
        ret = inShort ? "Treasures" : "Treasures From God's Word";
        break;
      case DIGGING:
        ret = inShort ? "Digging" : "Digging for Spiritual Gems";
        break;
      case F_RETURN_VISIT:
        ret = "First Return Visit";
        break;
      case S_RETURN_VISIT:
        ret = "Second Return Visit";
        break;
      case T_RETURN_VISIT:
        ret = "Third Return Visit";
        break;
      case APPLY_VIDEO:
        ret = "Apply Yourself to Reading and Teaching";
        break;
      case I_VIDEO:
        ret = "Initial Call Video";
        break;
      case F_RETURN_VISIT_VIDEO:
        ret = "First Return Visit Video";
        break;
      case S_RETURN_VISIT_VIDEO:
        ret = "Second Return Visit Video";
        break;
      case T_RETURN_VISIT_VIDEO:
        ret = "Third Return Visit Video";
        break;
      case B_VIDEO:
        ret = "Bible Study Video";
        break;
      case C_BIBLE_STUDY:
        ret = "Congregation Bible Study";
        break;
      case OPEN:
        ret = "Opening Comments";
        break;
      case REVIEW:
        ret = inShort ? "Review" : "Review/Preview/Announcements";
        break;
      case CO_TALK:
        ret = "Service Talk";
        break;

      default:
        ret = StringUtil.toTitle(this.name().replaceAll("_", " "));
        break;
    }
    return ret;
  }

  public boolean ambigous()
  {
    boolean ret = false;
    switch (this)
    {
      case F_RETURN_VISIT:
      case S_RETURN_VISIT:
      case T_RETURN_VISIT:
      case BIBLE_STUDY:
      case TALK:
        ret = true;
        break;
      default:
        break;
    }
    return ret;
  }

  public Part match(List<Part> inParts)
  {
    Part ret = this;
    for (Part value : inParts)
    {
      if (ret.getParent().equals(value.getParent()))
      {
        ret = value;
        break;
      }
    }
    return ret;
  }

  public Part assistant()
  {
    return C_BIBLE_STUDY.equals(this) ? READER : ASSISTANT;
  }

  public static List<Part> everyone()
  {
    List<Part> ret = new ArrayList<>();
    ret.add(INITIAL_CALL);
    ret.add(F_RETURN_VISIT);
    ret.add(S_RETURN_VISIT);
    ret.add(T_RETURN_VISIT);
    ret.add(BIBLE_STUDY);
    ret.add(ASSISTANT);
    return ret;
  }

  public static List<Part> male()
  {
    List<Part> ret = everyone();
    ret.add(BIBLE_READING);
    ret.add(TALK);
    return ret;
  }

  public static List<Part> servant()
  {
    List<Part> ret = male();
    ret.add(TREASURES);
    ret.add(DIGGING);
    ret.add(LIVING_1);
    ret.add(LIVING_2);
    ret.add(READER);
    return ret;
  }

  public static List<Part> elder()
  {
    List<Part> ret = servant();
    ret.add(CHAIRMAN);
    ret.add(C_BIBLE_STUDY);
    return ret;
  }

  public static List<Part> parts(Person inPerson)
  {
    List<Part> ret = null;
    if (inPerson.isElder())
    {
      ret = Part.elder();
    }
    else if (inPerson.isServant())
    {
      ret = Part.servant();
    }
    else if (inPerson.isMale())
    {
      ret = Part.male();
    }
    else
    {
      ret = Part.everyone();
    }
    return ret;
  }

  public static List<Part> schedule(boolean inCovisit, boolean inSlips, boolean inLiving2)
  {
    List<Part> ret = new ArrayList<>();
    if (!inSlips)
    {
      ret.add(Part.SONG_1);
      ret.add(Part.OPEN);
      ret.add(Part.TREASURES);
      ret.add(Part.DIGGING);
    }

    ret.add(Part.BIBLE_READING);
    ret.add(Part.APPLY1);
    ret.add(Part.APPLY2);
    ret.add(Part.APPLY3);
    ret.add(Part.APPLY4);

    if (!inSlips)
    {
      ret.add(Part.SONG_2);
      ret.add(Part.LIVING_1);

      if (inLiving2)
      {
        ret.add(Part.LIVING_2);
      }

      if (inCovisit)
      {
        ret.add(Part.REVIEW);
        ret.add(Part.CO_TALK);
      }
      else
      {
        ret.add(Part.C_BIBLE_STUDY);
        ret.add(Part.REVIEW);
      }

      ret.add(Part.SONG_3);
    }
    return ret;
  }

  public static String meeting(Part inPart)
  {
    String ret = null;
    switch (inPart)
    {
      case OPEN:
      case CHAIRMAN:
      case SONG_1:
      case EVENT:
        ret = "INTRO";
        break;
      case TREASURES:
      case DIGGING:
      case BIBLE_READING:
        ret = "TREASURES";
        break;
      case INITIAL_CALL:
      case APPLY1:
      case APPLY2:
      case APPLY3:
      case APPLY4:
      case RETURN_VISIT:
      case F_RETURN_VISIT:
      case S_RETURN_VISIT:
      case T_RETURN_VISIT:
      case BIBLE_STUDY:
      case TALK:
      case ASSISTANT:
      case F_RETURN_VISIT_VIDEO:
      case S_RETURN_VISIT_VIDEO:
      case T_RETURN_VISIT_VIDEO:
      case I_VIDEO:
      case B_VIDEO:
      case APPLY_VIDEO:
        ret = "APPLY";
        break;
      case C_BIBLE_STUDY:
      case CO_TALK:
      case SONG_2:
      case READER:
      case LIVING_1:
      case LIVING_2:
      case REVIEW:
      case SONG_3:
        ret = "LIVING";
        break;
    }
    return ret;
  }

  public Integer gDuration()
  {
    Integer ret = null;
    switch (this)
    {
      case SONG_1:
      case SONG_2:
      case SONG_3:
        ret = 5;
        break;
      case OPEN:
      case REVIEW:
        ret = 3;
        break;
      case DIGGING:
        ret = 8;
        break;
      case TREASURES:
        ret = 10;
        break;
      case CO_TALK:
      case C_BIBLE_STUDY:
        ret = 30;
        break;
      default:
        break;
    }
    return ret;
  }

  public String getMeetingTitle()
  {
    String ret = null;
    switch (this)
    {
      case CHAIRMAN:
      case TREASURES:
      case DIGGING:
      case SONG_1:
      case SONG_3:
        ret = "TREASURES FROM GOD'S WORD";
        break;

      case BIBLE_READING:
      case INITIAL_CALL:
      case F_RETURN_VISIT:
      case S_RETURN_VISIT:
      case T_RETURN_VISIT:
      case BIBLE_STUDY:
      case ASSISTANT:
      case TALK:
        ret = "APPLY YOURSELF TO THE FIELD MINISTRY";
        break;

      case LIVING_1:
      case LIVING_2:
      case C_BIBLE_STUDY:
      case READER:
        ret = "LIVING AS CHRISTIANS";
        break;

      default:
        break;
    }
    return ret;
  }

  public boolean isCbs()
  {
    return C_BIBLE_STUDY.equals(this);
  }
}