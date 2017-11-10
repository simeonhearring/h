package h.khall.client.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import h.khall.client.model.AssignmentPresenter.AssignDisplay;
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
  private Map<String, AssignDisplay> mAssigns;

  public AssignmentPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
    mAssigns = new HashMap<>();
  }


  public AssignmentPresenter handlers()
  {
    addHandler(RefreshEvent.TYPE, this);

    for (AssignDisplay value : mDisplay.getAssignDisplay())
    {
      value.setAddedCallback(this);
      mAssigns.put(value.getHall().name() + "|" + value.getPart().name(), value);
    }
    return this;
  }

  @Override
  public void dispatch(RefreshEvent inEvent)
  {
    Meeting meeting = client().getMeeting();
    Week week = meeting.getWeek(2017, 10, 2);
    mDisplay.setWeekOf(dateRange(week.getOf()));

    for (Entry<String, AssignDisplay> value : mAssigns.entrySet())
    {
      List<Tag> tags = week.getTags(value.getValue().getPart(), value.getValue().getHall());
      value.getValue().setValue(tags);
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

    Part getPart();

    List<Tag> getValues();

    void remove(Tag inTag);

    void setValue(List<Tag> inValue);

    void setValue(Tag... inValue);

    void setAddedCallback(CallBack<AssignDisplay> inCallBack);
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