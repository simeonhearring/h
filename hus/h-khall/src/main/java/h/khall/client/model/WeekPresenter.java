package h.khall.client.model;

import java.util.Date;
import java.util.List;

import h.khall.client.ui.event.AssignmentSavedEvent;
import h.khall.shared.command.AssignEmailCommand;
import h.khall.shared.command.AssignmentSaveCommand;
import h.model.shared.Tag;
import h.model.shared.khall.Assignment;
import h.model.shared.khall.Event;
import h.model.shared.khall.Hall;
import h.model.shared.khall.Meeting.Week;
import h.model.shared.khall.Part;
import h.model.shared.khall.Person;
import h.model.shared.khall.StudyPoint;
import h.style.g.client.model.CallBack;

public class WeekPresenter extends AbstractPresenter<WeekPresenter.Display>
  implements CallBack<WeekPresenter.AssignDisplay>
{
  // private boolean mEnabled = true;

  public WeekPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
//    mEnabled = isEdit(Security.OCLM);
  }

  public void reset()
  {
    mDisplay.setWeekOf(null);
    for (AssignDisplay value : mDisplay.getAssignDisplay())
    {
      value.setCallback(null);
      value.setVisible(false);
      value.setIconVisible(false);
      value.setEmailTip(null);
      value.removeAll();
    }
    mDisplay.clearEvents();
  }

  public void email(Assignment... inAssignment)
  {
    fire(new AssignEmailCommand(mProfile.getUserId(), mClient.getCong().gMidweekOn(), inAssignment));
  }

  public void setWeek(Week inWeek)
  {
    Date of = inWeek.getOf();
    boolean validWeek = of != null;

    if (validWeek)
    {
      mDisplay.setWeekOf(dateRange(of));

      for (Event value : mClient.gEvents(of))
      {
        mDisplay.addEvent(value.getDisplay());
      }

      for (AssignDisplay value : mDisplay.getAssignDisplay())
      {
        Part ppart = value.getPpart();
        Hall hall = value.getHall();

        Assignment assignment = inWeek.gAssignment(ppart, hall);
        if (assignment != null)
        {
          value.setAssignment(assignment);
          value.setValue(mClient.getTags(assignment));
          value.setPart(assignment.getPart());
          emailIcon(assignment, value);

          value.setVisible(true);
          value.setCallback(this);
        }
      }
    }
    mDisplay.setVisible(validWeek);
  }

  private void emailIcon(Assignment inAssignment, AssignDisplay inDisplay)
  {
    boolean show = false;
    String tip = null;
    if (inAssignment.isAssigned())
    {
      Person person = gPersonEmail(inAssignment);
      show = person.isEmail();
      tip = "mailto:" + person.getEmail();
    }
    inDisplay.setEmailTip(tip);
    inDisplay.setIconVisible(show);
  }

  private Person gPersonEmail(Assignment inAssignment)
  {
    return mClient.gPerson(inAssignment.getParticipantId());
  }

  @Override
  public void onCallBack(AssignDisplay inDisplay)
  {
    // if (mEnabled)
    // {
      save(inDisplay);
    // }
    // else
    // {
    // Assignment assignment = inDisplay.getAssignment();
    // inDisplay.setValue(mClient.getTags(assignment));
    // }
  }

  private void save(AssignDisplay inDisplay)
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

    if (par == null && st != null)
    {
      ass = null;
      st = null;
      inDisplay.removeAll();
    }

    assignment.setParticipantId(par);
    assignment.setAssistantId(ass);
    assignment.setStudyPoint(st);

    emailIcon(assignment, inDisplay);

    fire(new AssignmentSaveCommand(assignment), new AssignmentSavedEvent());
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

    void setIconVisible(boolean inVisible);

    void setEmailTip(String inText);
  }

  public interface Display extends h.style.g.client.model.Display
  {
    AssignDisplay[] getAssignDisplay();

    void addEvent(String inDisplay);

    void setWeekOf(String inText);

    void setWeek(Week inWeek);

    void reset();

    void clearEvents();
  }
}