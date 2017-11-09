package h.model.shared;

import h.model.shared.util.StringUtil;

public enum Part
{
  CHAIRMAN,
  PRAYER_1,
  TREASURES,
  DIGGING,
  BIBLE_READING,
  INITIAL_CALL,
  F_RETURN_VISIT,
  S_RETURN_VISIT,
  T_RETURN_VISIT,
  BIBLE_STUDY,
  TALK,
  LIVING_1,
  LIVING_2,
  C_BIBLE_STUDY,
  PRAYER_2,;

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
      case PRAYER_1:
        ret = "Opening Prayer";
        break;
      case PRAYER_2:
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
}