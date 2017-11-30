package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.TabListItem;
import org.gwtbootstrap3.client.ui.TabPane;
import org.gwtbootstrap3.client.ui.gwt.HTMLPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.MonthPresenter;
import h.khall.client.model.WeekPresenter.Display;
import h.model.shared.khall.Meeting.Month;
import h.style.g.client.ui.event.ReportEvent;

public class MonthView extends AbstractView<MonthPresenter>
  implements MonthPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, MonthView>
  {
  }

  @UiField
  CloseableView mHead;

  @UiField
  HTMLPanel mTop;

  @UiField
  WeekView mW0, mW1, mW2, mW3, mW4;

  @UiField
  TabListItem mT1, mT2, mT3, mT4, mT5;

  @UiField
  TabPane mC1, mC2, mC3, mC4, mC5;

  @UiField
  Anchor mSchedule, mSlips, mWorksheet;

  public MonthView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new MonthPresenter(this);
    id(generateId(), mT1, mC1);
    id(generateId(), mT2, mC2);
    id(generateId(), mT3, mC3);
    id(generateId(), mT4, mC4);
    id(generateId(), mT5, mC5);
  }

  private static void id(String inId, TabListItem inItem, TabPane inPane)
  {
    inItem.setDataTarget("#" + inId);
    inPane.setId(inId);
  }

  @UiHandler(
  {
      "mSchedule", "mSlips", "mWorksheet"
  })
  public void onClick(ClickEvent inEvent)
  {
    int congId = mPresenter.getCongId();
    int year = mPresenter.getYear();
    int month = mPresenter.getMonth();

    Object source = inEvent.getSource();
    if (mSchedule.equals(source))
    {
      fire(new ReportEvent(MESSAGE.rptS140(congId, year, month)));
    }
    else if (mSlips.equals(source))
    {
      fire(new ReportEvent(MESSAGE.rptS89(congId, year, month)));
    }
    else if (mWorksheet.equals(source))
    {
      fire(new ReportEvent(MESSAGE.rptOclmWork(congId, year, month)));
    }
  }

  @Override
  public void reset()
  {
    mPresenter.reset();
  }

  @Override
  public void setMonth(int inYear, int inMo, Month inMonth)
  {
    mPresenter.setMonth(inYear, inMo, inMonth);
  }

  @Override
  public void setVisible(boolean inVisible)
  {
    mTop.setVisible(inVisible);
  }

  @Override
  public void setMonth(String inText)
  {
    mHead.setHeading(inText);
  }

  @Override
  public Display getWeek0()
  {
    return mW0;
  }

  @Override
  public Display getWeek1()
  {
    return mW1;
  }

  @Override
  public Display getWeek2()
  {
    return mW2;
  }

  @Override
  public Display getWeek3()
  {
    return mW3;
  }

  @Override
  public Display getWeek4()
  {
    return mW4;
  }

  @Override
  protected void onLoad()
  {
    slimScroll();
  }
}