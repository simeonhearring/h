package h.model.shared.khall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import h.model.shared.util.StringUtil;
import h.model.shared.util.TimeUtil;

@SuppressWarnings("serial")
public class Assignment extends Schedule implements Serializable
{
  private Long mParticipantId;
  private Long mAssistantId;
  private StudyPoint mStudyPoint;

  public Hall getHall()
  {
    return getSchool();
  }

  public StudyPoint gStudyPoint()
  {
    return mStudyPoint == null ? getCurriculum().getStudyPoint() : null;
  }

  public StudyPoint getStudyPoint()
  {
    return mStudyPoint;
  }

  public void setStudyPoint(StudyPoint inStudyPoint)
  {
    mStudyPoint = inStudyPoint;
  }

  public boolean isStudent()
  {
    return getPart().isStudyPoint();
  }

  public boolean isAssigned()
  {
    return isParticipant() && isAssistant();// && isStudyPoint();
  }

  public boolean isParticipant()
  {
    return mParticipantId != null;
  }

  public boolean isAssistant()
  {
    boolean ret = true;
    if (getPart().isAssisted())
    {
      ret = mAssistantId != null;
    }
    return ret;
  }

  public boolean isStudyPoint()
  {
    boolean ret = true;
    if (getPart().isStudyPoint())
    {
      ret = mStudyPoint != null;
    }
    return ret;
  }

  @Override
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("Assignment [mWeekOf=");
    builder.append(getDate());
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

  public static List<String> getArchive(Persons inPersons, List<Assignment> inArchive,
      Long inParticipantId)
  {
    List<String> ret = new ArrayList<>();
    for (Assignment value : inArchive)
    {
      ret.add(value.gHistoryLine(inPersons, inParticipantId));
    }
    return ret;
  }

  public String gHistoryLine(Persons inPersons, Long inParticipantId)
  {
    Date wo = getDate();
    String sp = mStudyPoint != null ? mStudyPoint.item() : "";
    String pt = getPart().getLabel(true);
    String at = inPersons.gName(mAssistantId);

    if (inParticipantId.equals(mAssistantId))
    {
      pt = getPart().assistant().getLabel(true);
      sp = "";
      at = inPersons.gName(mParticipantId);
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

  public String gTiming()
  {
    Integer min = getDurationMinutes();
    if (min == null || getPart().isSong())
    {
      return null;
    }

    return min + " min." + (getPart().isOrless() ? " or less" : "");
  }

  public String gPartTimeLabel()
  {
    return getPart().getLabel(false) + StringUtil.ensure(gTiming(), " ");
  }
}