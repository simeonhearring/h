package h.khall.client.model;

import java.util.Date;
import java.util.List;

import h.khall.client.model.AssignmentPresenter.AssignDisplay;
import h.khall.shared.model.Assignment;
import h.khall.shared.model.Hall;
import h.khall.shared.model.Meeting;
import h.khall.shared.model.Meeting.Week;
import h.khall.shared.model.Part;
import h.model.shared.Tag;
import h.style.g.client.model.CallBack;
import h.style.g.client.ui.event.RefreshEvent;

public class AssignmentPresenter extends AbstractPresenter<AssignmentPresenter.Display>
  implements CallBack<AssignDisplay>, RefreshEvent.Handler
{
  public AssignmentPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public AssignmentPresenter handlers()
  {
    addHandler(RefreshEvent.TYPE, this);

    for (AssignDisplay value : mDisplay.getAssignDisplay())
    {
      value.setAddedCallback(this);
    }
    return this;
  }

  @Override
  public void dispatch(RefreshEvent inEvent)
  {
    Meeting meeting = mClient.getMeeting();
    Week week = meeting.getWeek(2017, 0, 2); // TODO
    mDisplay.setWeekOf(dateRange(week.getOf()));

    for (AssignDisplay value : mDisplay.getAssignDisplay())
    {
      value.clear();

      value.setVisible(week.contains(value.getPart()));
      value.setPart(week.getPart(value.getPart()));

      Assignment assignment = week.get(value.getPart().getParent(), value.getHall());
      if (assignment != null)
      {
        value.setValue(assignment.getTags());
      }
    }
  }

  @Override
  public void onCallBack(AssignDisplay inDisplay)
  {
    for (Tag value : inDisplay.getValues())
    {
      mDisplay.notify("Callback: " + value.getName());
    }
  }

  public interface AssignDisplay
  {
    Hall getHall();

    void setVisible(boolean inVisible);

    Part getPart();

    List<Tag> getValues();

    void clear();

    void remove(Tag inTag);

    void setValue(List<Tag> inValue);

    void setValue(Tag... inValue);

    void setLabel(String inText);

    void setAddedCallback(CallBack<AssignDisplay> inCallBack);

    void setColor(String inColor);

    void setPart(Part inPart);
  }

  public interface Display extends h.style.g.client.model.Display
  {
    AssignDisplay[] getAssignDisplay();

    void setWeekOf(String inText);
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
}