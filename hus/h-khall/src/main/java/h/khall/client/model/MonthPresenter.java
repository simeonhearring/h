package h.khall.client.model;

import java.util.Date;

import h.model.shared.khall.Meeting.Month;

public class MonthPresenter extends AbstractPresenter<MonthPresenter.Display>
{
  private int mYear, mMonth;

  public MonthPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public void reset()
  {
    mDisplay.setMonth(null);
    mDisplay.setVisible(false);
    mDisplay.getWeek0().reset();
    mDisplay.getWeek1().reset();
    mDisplay.getWeek2().reset();
    mDisplay.getWeek3().reset();
    mDisplay.getWeek4().reset();
  }

  public int getCongId()
  {
    return mProfile.getCongId();
  }

  public int getYear()
  {
    return mYear;
  }

  public int getMonth()
  {
    return mMonth;
  }

  public void setMonth(int inYear, int inMo, Month inMonth)
  {
    mYear = inYear;
    mMonth = inMo;
    @SuppressWarnings("deprecation")
    Date d = new Date(mYear - 1900, mMonth, 1);
    mDisplay.setMonth(format("MMMM yyyy", d).toUpperCase());

    if (!inMonth.empty())
    {
      mDisplay.getWeek0().setWeek(inMonth.g(0));
      mDisplay.getWeek1().setWeek(inMonth.g(1));
      mDisplay.getWeek2().setWeek(inMonth.g(2));
      mDisplay.getWeek3().setWeek(inMonth.g(3));
      mDisplay.getWeek4().setWeek(inMonth.g(4));
      mDisplay.setVisible(true);
    }
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void reset();

    void setMonth(int inYear, int inMo, Month inMonth);

    void setMonth(String inText);

    WeekPresenter.Display getWeek0();

    WeekPresenter.Display getWeek1();

    WeekPresenter.Display getWeek2();

    WeekPresenter.Display getWeek3();

    WeekPresenter.Display getWeek4();
  }
}