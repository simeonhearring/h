package h.khall.client.model;

import java.util.Date;

import h.model.shared.khall.Meeting;
import h.style.g.client.ui.event.RefreshEvent;

public class MidweekPresenter extends AbstractPresenter<MidweekPresenter.Display>
  implements RefreshEvent.Handler
{
  private int[][] mRange = {{0,1,2}, {3,4,5}, {6,7,8}, {9,10,11}};
  private int mPageIndex = 0;

  public MidweekPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
    mPageIndex = currentIndex();
  }

  public MidweekPresenter handlers()
  {
    addHandler(RefreshEvent.TYPE, this);
    return this;
  }

  public void previous()
  {
    mPageIndex = mPageIndex == 0 ? mRange.length - 1 : mPageIndex - 1;
    addMonths();
  }

  public void next()
  {
    mPageIndex = mPageIndex == mRange.length - 1 ? 0 : mPageIndex + 1;
    addMonths();
  }

  @Override
  public void dispatch(RefreshEvent inEvent)
  {
    addMonths();
  }

  private void addMonths()
  {
    Meeting meeting = mClient.getMeeting();

    int yr = meeting.getYear();

    int mo0 = mRange[mPageIndex][0];
    int mo1 = mRange[mPageIndex][1];
    int mo2 = mRange[mPageIndex][2];

    mDisplay.getMonth0().setMonth(yr, mo0, meeting.getMonth(yr, mo0));
    mDisplay.getMonth1().setMonth(yr, mo1, meeting.getMonth(yr, mo1));
    mDisplay.getMonth2().setMonth(yr, mo2, meeting.getMonth(yr, mo2));
  }

  private int currentIndex()
  {
    int ret = 0;
    @SuppressWarnings("deprecation")
    int mo = new Date().getMonth();
    for (int i = 0; i < mRange.length; i++)
    {
      for (int value : mRange[i])
      {
        if (mo == value)
        {
          ret = i;
          break;
        }
      }
    }
    return ret;
  }

  public interface Display extends h.style.g.client.model.Display
  {
    MonthPresenter.Display getMonth0();

    MonthPresenter.Display getMonth1();

    MonthPresenter.Display getMonth2();
  }
}