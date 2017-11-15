package h.khall.shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import h.model.shared.Tag;

@SuppressWarnings("serial")
public class Assignment implements Serializable
{
  private Date mWeekOf;
  private Part mPart;
  private Hall mHall;
  private Person mParticipant;
  private Person mAssistant;
  private StudyPoint mStudyPoint;

  public Date getWeekOf()
  {
    return mWeekOf;
  }

  public void setWeekOf(Date inWeekOf)
  {
    mWeekOf = inWeekOf;
  }

  public Part getPart()
  {
    return mPart;
  }

  public void setPart(Part inPart)
  {
    mPart = inPart;
  }

  public Hall getHall()
  {
    return mHall;
  }

  public void setHall(Hall inHall)
  {
    mHall = inHall;
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
    builder.append(mWeekOf);
    builder.append(", mPart=");
    builder.append(mPart);
    builder.append(", mHall=");
    builder.append(mHall);
    builder.append(", mParticipant=");
    builder.append(mParticipant);
    builder.append("]");
    return builder.toString();
  }
}