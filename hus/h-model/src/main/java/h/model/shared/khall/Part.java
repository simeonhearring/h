package h.model.shared.khall;

import java.util.List;

import h.model.shared.util.StringUtil;

public enum Part
{
  READER,
  RETURN_VISIT,
  ASSISTANT,
  APPLY1,
  APPLY2,
  APPLY3,
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
  SONG_3,;

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

  public static Part[] student()
  {
    return new Part[]
    {
        BIBLE_READING,
        INITIAL_CALL,
        F_RETURN_VISIT,
        S_RETURN_VISIT,
        T_RETURN_VISIT,
        TALK,
        BIBLE_STUDY
    };
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

  public String getLabel(boolean inShort)
  {
    String ret = null;
    switch (this)
    {
      case SONG_1:
        ret = "Opening Prayer";
        break;
      case SONG_3:
        ret = "Closing Prayer";
        break;
      case TREASURES:
        ret = inShort ? "Treasures" : "Treasures From God's Word";
        break;
      case DIGGING:
        ret = inShort ? "Digging" : "Digging For Spiritual Gems";
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
      case C_BIBLE_STUDY:
        ret = "Congregation Bible Study";
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
}