package h.model.shared.khall;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Curriculum implements Serializable
{
  private Long mId;
  private Date mDate;
  private Part mPart;
  private String mTheme;
  private String mSource;
  private Integer mDurationMinutes;
  private Integer mSort; // Apply1=7, Apply2=8, Apply3=9, Apply4=10
  private StudyPoint mStudyPoint;

  public Part getPpart()
  {
    Part ret = mPart;
    switch (mPart)
    {
      case APPLY_VIDEO:
      case I_VIDEO:
      case F_RETURN_VISIT_VIDEO:
      case S_RETURN_VISIT_VIDEO:
      case T_RETURN_VISIT_VIDEO:
      case B_VIDEO:
      case INITIAL_CALL:
      case RETURN_VISIT:
      case F_RETURN_VISIT:
      case S_RETURN_VISIT:
      case T_RETURN_VISIT:
      case TALK:
      case BIBLE_STUDY:
        ret = sortpart();
        break;
      default:
        break;
    }
    return ret;
  }

  private Part sortpart()
  {
    Part ret = null;
    switch (mSort)
    {
      case 7:
        ret = Part.APPLY1;
        break;
      case 8:
        ret = Part.APPLY2;
        break;
      case 9:
        ret = Part.APPLY3;
        break;
      case 10:
        ret = Part.APPLY4;
        break;
    }
    return ret;
  }

  public Date getDate()
  {
    return mDate;
  }

  public void setDate(Date inDate)
  {
    mDate = inDate;
  }

  public Part getPart()
  {
    return mPart;
  }

  public void setPart(Part inPart)
  {
    mPart = inPart;
  }

  public String getTheme()
  {
    return mTheme;
  }

  public void setTheme(String inTheme)
  {
    mTheme = inTheme;
  }

  public String getSource()
  {
    return mSource;
  }

  public void setSource(String inSource)
  {
    mSource = inSource;
  }

  public Integer getDurationMinutes()
  {
    return mDurationMinutes;
  }

  public void setDurationMinutes(Integer inDurationMinutes)
  {
    mDurationMinutes = inDurationMinutes;
  }

  public Integer getSort()
  {
    return mSort;
  }

  public void setSort(Integer inSort)
  {
    mSort = inSort;
  }

  public Long getId()
  {
    return mId;
  }

  public void setId(Long inId)
  {
    mId = inId;
  }

  public long gElapse()
  {
    long ret = 0;

    if (mDurationMinutes != null)
    {
      if (mPart.isStudyPoint())
      {
        ret = mDurationMinutes + 1;
      }
      else
      {
        ret = mDurationMinutes;
      }
    }

    return ret * 60000;
  }

  public StudyPoint getStudyPoint()
  {
    return mStudyPoint;
  }

  public void setStudyPoint(StudyPoint inStudyPoint)
  {
    mStudyPoint = inStudyPoint;
  }
}