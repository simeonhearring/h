package h.model.shared.khall;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import h.model.shared.util.StringUtil;

@SuppressWarnings("serial")
public class Event implements Serializable
{
  public enum Type
  {
    WEEKEND,
    WEEKMID,
    CO_VISIT,
    CACO,
    CABR,
    REGIONAL;
  }

  private Type mType;
  private String mTheme;

  Event()
  {
  }

  public Event(Type inType, String inTheme)
  {
    mType = inType;
    mTheme = inTheme;
  }

  public Type getType()
  {
    return mType;
  }

  public void setType(Type inType)
  {
    mType = inType;
  }

  public String getTheme()
  {
    return mTheme;
  }

  public void setTheme(String inTheme)
  {
    mTheme = inTheme;
  }

  @JsonIgnore
  public boolean isCoVisit()
  {
    return Type.CO_VISIT.equals(mType);
  }

  public String getDisplay()
  {
    return gTypeLabel() + StringUtil.ensure(mTheme, ": ");
  }

  private String gTypeLabel()
  {
    String ret = "";
    switch (mType)
    {
      case CABR:
        ret = "Circuit Assembly - BR";
        break;
      case CACO:
        ret = "Circuit Assembly - CO";
        break;
      case CO_VISIT:
        ret = "Circuit Overseer Visit";

        break;
      case REGIONAL:
        ret = "Regional Convention";
        break;
      case WEEKEND:
        ret = "Weekend Meeting";

        break;
      case WEEKMID:
        ret = "Midweek Meeting";
        break;
      default:
        break;
    }
    return ret;
  }
}