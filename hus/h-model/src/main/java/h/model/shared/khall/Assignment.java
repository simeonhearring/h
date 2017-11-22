package h.model.shared.khall;

import java.io.Serializable;
import java.util.Date;

import h.model.shared.util.StringUtil;
import h.model.shared.util.TimeUtil;

@SuppressWarnings("serial")
public class Assignment extends Schedule implements Serializable
{
  private Long mParticipantId;
  private Long mAssistantId;
  private StudyPoint mStudyPoint;

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

  public StudyPoint getStudyPoint()
  {
    return mStudyPoint;
  }

  public void setStudyPoint(StudyPoint inStudyPoint)
  {
    mStudyPoint = inStudyPoint;
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
    builder.append(", mParticipantId=");
    builder.append(mParticipantId);
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

  public String gHistoryLine(Persons inPersons, Long inParticipantId)
  {
    Date wo = getWeekOf();
    String sp = mStudyPoint != null ? mStudyPoint.item() : "";
    String pt = getPart().getLabel(true);
    String at = inPersons.gName(mAssistantId);

    if (inParticipantId == mAssistantId)
    {
      pt = getPart().assistant().getLabel(true);
      sp = "";
      at = "";
    }

    StringBuilder sb = new StringBuilder();
    sb.append(TimeUtil.format("MM-dd-yy", wo)).append(" ");
    sb.append(pt).append(" ");
    sb.append(sp).append(" ");
    sb.append(StringUtil.ensure(at, "--"));

    return sb.toString();
  }

  public boolean isAssignedTo(Long inId)
  {
    return inId.equals(mParticipantId) || inId.equals(mAssistantId);
  }
}