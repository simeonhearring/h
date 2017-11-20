package h.khall.client.model;

import java.util.Date;
import java.util.List;

import h.khall.shared.command.AssignmentSaveCommand;
import h.model.shared.Tag;
import h.model.shared.khall.Assignment;
import h.model.shared.khall.Hall;
import h.model.shared.khall.Meeting.Week;
import h.model.shared.khall.Part;
import h.model.shared.khall.Person;
import h.model.shared.khall.StudyPoint;
import h.style.g.client.model.CallBack;

public class WeekPresenter extends AbstractPresenter<WeekPresenter.Display>
  implements CallBack<WeekPresenter.AssignDisplay>
{
  public WeekPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public void reset()
  {
    mDisplay.setWeekOf(null);
    for (AssignDisplay value : mDisplay.getAssignDisplay())
    {
      value.setCallback(null);
      value.removeAll();
    }
  }

  public void setWeek(Week inWeek)
  {
    Date of = inWeek.getOf();

    if (of != null)
    {
      mDisplay.setWeekOf(dateRange(of));

      for (AssignDisplay value : mDisplay.getAssignDisplay())
      {
        Part ppart = value.getPpart();
        Hall hall = value.getHall();
        Assignment assignment = inWeek.get(ppart, hall);
        boolean contains = assignment != null;
        value.setVisible(contains);
        if (contains)
        {
          value.setAssignment(assignment);
          value.setValue(mClient.getTags(assignment));
          value.setPart(assignment.getPart());

          value.setCallback(this);
        }
        else
        {
          mDisplay.console("Alert", "Week of " + of + " does not have a " + ppart + " part.");
        }
      }
    }
    mDisplay.setVisible(of != null);
  }

  @Override
  public void onCallBack(AssignDisplay inDisplay)
  {
    Assignment assignment = inDisplay.getAssignment();

    Long par = null;
    Long ass = null;
    StudyPoint st = null;

    for (Tag value : inDisplay.getValues())
    {
      if (value instanceof Person)
      {
        if (par == null)
        {
          par = ((Person) value).getIdLong();
        }
        else if (inDisplay.getPart().isAssisted() && ass == null)
        {
          ass = ((Person) value).getIdLong();
        }
        else
        {
          inDisplay.remove(value);
          mDisplay.notify("No more participants needed for this part.");
        }
      }
      else if (value instanceof StudyPoint)
      {
        if (inDisplay.getPart().isStudyPoint())
        {
          if (st == null)
          {
            st = (StudyPoint) value;
          }
          else
          {
            inDisplay.remove(value);
            mDisplay.notify("Only 1 study point allowed.");
          }
        }
      }
    }

    if (par == null && (ass == null || st == null))
    {
      ass = null;
      st = null;
      inDisplay.removeAll();
      mDisplay.notify("Please add participant first.");
    }

    assignment.setParticipantId(par);
    assignment.setAssistantId(ass);
    assignment.setStudyPoint(st);

    fire(new AssignmentSaveCommand(assignment));
  }

  @SuppressWarnings("deprecation")
  private String dateRange(Date inValue)
  {
    String ret = null;
    if (inValue != null)
    {
      Date end = new Date(inValue.getTime() + 518400000); // 6 days
      String pattern = inValue.getMonth() == end.getMonth() ? "dd" : "MMMM dd";
      ret = format("MMMM dd-", inValue) + format(pattern, end);
    }
    return ret;
  }

  public interface AssignDisplay
  {
    Hall getHall();

    Part getPart();

    Part getPpart();

    void setAssignment(Assignment inAssignment);

    Assignment getAssignment();

    void setVisible(boolean inVisible);

    List<Tag> getValues();

    void removeAll();

    void remove(Tag inTag);

    void setValue(List<Tag> inValue);

    void setValue(Tag... inValue);

    void setLabel(String inText);

    void setCallback(CallBack<AssignDisplay> inCallBack);

    void setColor(String inColor);

    void setPart(Part inPart);
  }

  public interface Display extends h.style.g.client.model.Display
  {
    AssignDisplay[] getAssignDisplay();

    void setWeekOf(String inText);

    void setWeek(Week inWeek);

    void reset();
  }
}