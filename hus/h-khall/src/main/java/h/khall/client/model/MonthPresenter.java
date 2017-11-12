package h.khall.client.model;

import h.khall.shared.model.Meeting;
import h.khall.shared.model.Meeting.Month;
import h.style.g.client.ui.event.RefreshEvent;

public class MonthPresenter extends AbstractPresenter<MonthPresenter.Display>
  implements RefreshEvent.Handler
{
  public MonthPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public MonthPresenter handlers()
  {
    addHandler(RefreshEvent.TYPE, this);
    return this;
  }

  @Override
  public void dispatch(RefreshEvent inEvent)
  {
    Meeting meeting = mClient.getMeeting();

    Month month = meeting.getMonth(2017, 0);

    mDisplay.getWeek0().setWeek(month.g(0));
    mDisplay.getWeek1().setWeek(month.g(1));
    mDisplay.getWeek2().setWeek(month.g(2));
    mDisplay.getWeek3().setWeek(month.g(3));
    mDisplay.getWeek4().setWeek(month.g(4));
  }

  public interface Display extends h.style.g.client.model.Display
  {
    WeekPresenter.Display getWeek0();

    WeekPresenter.Display getWeek1();

    WeekPresenter.Display getWeek2();

    WeekPresenter.Display getWeek3();

    WeekPresenter.Display getWeek4();
  }
}