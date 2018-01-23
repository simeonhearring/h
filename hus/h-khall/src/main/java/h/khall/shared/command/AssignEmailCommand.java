package h.khall.shared.command;

import java.util.Date;

import h.model.shared.khall.Assignment;
import h.style.g.shared.command.AbstractCommand;
import h.style.g.shared.rpc.CommandName;

@SuppressWarnings("serial")
@CommandName("AssignEmailCommand")
public class AssignEmailCommand extends AbstractCommand
{
  private String mFrom;
  private Assignment[] mAssignment;
  private Date mMeetingDay;

  public AssignEmailCommand()
  {
  }

  public AssignEmailCommand(String inFrom, Date inMeetingDay, Assignment... inAssignment)
  {
    mFrom = inFrom;
    mMeetingDay = inMeetingDay;
    mAssignment = inAssignment;
  }

  public String getFrom()
  {
    return mFrom;
  }

  public Date getMeetingDay()
  {
    return mMeetingDay;
  }

  public Assignment[] getAssignment()
  {
    return mAssignment;
  }
}