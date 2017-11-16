package h.khall.client.model;

import java.util.Date;

import h.model.shared.khall.Meeting.Month;

public class MonthPresenter extends AbstractPresenter<MonthPresenter.Display>
{
  public MonthPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public void setMonth(int inYear, int inMo, Month inMonth)
  {
    @SuppressWarnings("deprecation")
    Date d = new Date(inYear - 1900, inMo, 1);
    mDisplay.setMonth(format("MMMM yyyy", d).toUpperCase());

    mDisplay.getWeek0().setWeek(inMonth.g(0));
    mDisplay.getWeek1().setWeek(inMonth.g(1));
    mDisplay.getWeek2().setWeek(inMonth.g(2));
    mDisplay.getWeek3().setWeek(inMonth.g(3));
    mDisplay.getWeek4().setWeek(inMonth.g(4));
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void setMonth(int inYear, int inMo, Month inMonth);

    void setMonth(String inText);

    WeekPresenter.Display getWeek0();

    WeekPresenter.Display getWeek1();

    WeekPresenter.Display getWeek2();

    WeekPresenter.Display getWeek3();

    WeekPresenter.Display getWeek4();
  }
}