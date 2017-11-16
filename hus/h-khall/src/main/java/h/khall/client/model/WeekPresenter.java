package h.khall.client.model;

import java.util.Date;
import java.util.List;

import h.khall.shared.command.AssignmentSaveCommand;
import h.model.shared.Tag;
import h.model.shared.khall.Assignment;
import h.model.shared.khall.Hall;
import h.model.shared.khall.Part;
import h.model.shared.khall.Person;
import h.model.shared.khall.StudyPoint;
import h.model.shared.khall.Meeting.Week;
import h.style.g.client.model.CallBack;

public class WeekPresenter extends AbstractPresenter<WeekPresenter.Display>
  implements CallBack<WeekPresenter.AssignDisplay>
{
  public WeekPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public void setWeek(Week inWeek)
  {
    Date of = inWeek.getOf();

    mDisplay.setWeekOf(dateRange(of));

    for (AssignDisplay value : mDisplay.getAssignDisplay())
    {
      value.clear();
      value.setCallback(null);

      value.setVisible(inWeek.contains(value.getPart()));
      value.setPart(inWeek.getPart(value.getPart()));

      Assignment assignment = inWeek.get(value.getPart().getParent(), value.getHall());
      value.setAssignment(assignment);
      value.setValue(assignment.getTags());

      value.setCallback(this);
    }

    mDisplay.setVisible(of != null);
  }

  @Override
  public void onCallBack(AssignDisplay inDisplay)
  {
    Assignment assignment = inDisplay.getAssignment();

    Person par = null;
    Person ass = null;
    StudyPoint st = null;

    for (Tag value : inDisplay.getValues())
    {
      if (value instanceof Person)
      {
        if (par == null)
        {
          par = (Person) value;
        }
        else if (inDisplay.getPart().isAssisted() && ass == null)
        {
          ass = (Person) value;
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

    assignment.setParticipant(par);
    assignment.setAssistant(ass);
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

    void setAssignment(Assignment inAssignment);

    Assignment getAssignment();

    void setVisible(boolean inVisible);

    List<Tag> getValues();

    void clear();

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
  }
}