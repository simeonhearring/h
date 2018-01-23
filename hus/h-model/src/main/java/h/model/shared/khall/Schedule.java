package h.model.shared.khall;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Schedule implements Serializable
{
  private Long mId;

  private Curriculum mCurriculum;
  private Long mCongregation;
  private Hall mSchool;

  public Long getCongregation()
  {
    return mCongregation;
  }

  public void setCongregation(Long inCongregation)
  {
    mCongregation = inCongregation;
  }

  public Curriculum getCurriculum()
  {
    return mCurriculum;
  }

  public void setCurriculum(Curriculum inCurriculum)
  {
    mCurriculum = inCurriculum;
  }

  public Hall getSchool()
  {
    return mSchool;
  }

  public void setSchool(Hall inSchool)
  {
    mSchool = inSchool;
  }

  public Long getId()
  {
    return mId;
  }

  public void setId(Long inId)
  {
    mId = inId;
  }

  public Part getPpart()
  {
    return mCurriculum.getPpart();
  }

  public Date getDate()
  {
    return mCurriculum.getDate();
  }

  public Part getPart()
  {
    return mCurriculum.getPart();
  }

  public String getTheme()
  {
    return mCurriculum.getTheme();
  }

  public String getSource()
  {
    return mCurriculum.getSource();
  }

  public Integer getDurationMinutes()
  {
    return mCurriculum.getDurationMinutes();
  }

  public Integer getSort()
  {
    return mCurriculum.getSort();
  }
}