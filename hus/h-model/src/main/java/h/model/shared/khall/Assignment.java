package h.model.shared.khall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import h.model.shared.Tag;

@SuppressWarnings("serial")
public class Assignment extends Schedule implements Serializable
{
  private StudyPoint mStudyPoint;
  private Long mParticipantId;
  private Long mAssistantId;

  private Person mParticipant;
  private Person mAssistant;

  public Date getWeekOf()
  {
    return getCurriculum().getDate();
  }

  public Part getPart()
  {
    return getCurriculum().getPart();
  }

  public Hall getHall()
  {
    return getSchool();
  }

  public Person getParticipant()
  {
    return mParticipant;
  }

  public void setParticipant(Person inParticipant)
  {
    mParticipant = inParticipant;
  }

  public Person getAssistant()
  {
    return mAssistant;
  }

  public void setAssistant(Person inAssistant)
  {
    mAssistant = inAssistant;
  }

  public StudyPoint getStudyPoint()
  {
    return mStudyPoint;
  }

  public void setStudyPoint(StudyPoint inStudyPoint)
  {
    mStudyPoint = inStudyPoint;
  }

  public List<Tag> getTags()
  {
    List<Tag> ret = new ArrayList<>();
    addTags(ret);
    return ret;
  }

  private void addTags(List<Tag> inTags)
  {
    if (mParticipant != null)
    {
      inTags.add(mParticipant);
      if (mAssistant != null)
      {
        inTags.add(mAssistant);
      }
      if (mStudyPoint != null)
      {
        inTags.add(mStudyPoint);
      }
    }
  }

  @Override
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("Assignment [mWeekOf=");
    builder.append(getWeekOf());
    builder.append(", mPart=");
    builder.append(getPart());
    builder.append(", mHall=");
    builder.append(getHall());
    builder.append(", mSort=");
    builder.append(getCurriculum().getSort());
    builder.append(", mParticipant=");
    builder.append(mParticipant);
    builder.append("]");
    return builder.toString();
  }

  public Long getParticipantId()
  {
    return mParticipantId;
  }

  public void setParticipantId(Long inParticipantId)
  {
    mParticipantId = inParticipantId;
  }

  public Long getAssistantId()
  {
    return mAssistantId;
  }

  public void setAssistantId(Long inAssistantId)
  {
    mAssistantId = inAssistantId;
  }

  public void ids()
  {
    mParticipantId = mParticipant == null ? null : mParticipant.getIdLong();
    mAssistantId = mAssistant == null ? null : mAssistant.getIdLong();
  }
}