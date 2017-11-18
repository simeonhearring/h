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
  private Long mDurationMinutes;
  private Long mSort;

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

  // @com.fasterxml.jackson.annotation.JsonIgnore
  public Long getDurationMinutes()
  {
    return mDurationMinutes;
  }

  public void setDurationMinutes(Long inDurationMinutes)
  {
    mDurationMinutes = inDurationMinutes;
  }

  public Long getSort()
  {
    return mSort;
  }

  public void setSort(Long inSort)
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
}