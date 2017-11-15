package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.MonthPresenter;
import h.khall.client.model.WeekPresenter.Display;
import h.khall.shared.model.Meeting.Month;

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
  WeekView mW0, mW1, mW2, mW3, mW4;

  public MonthView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new MonthPresenter(this);
  }

  @Override
  public void setMonth(int inYear, int inMo, Month inMonth)
  {
    mPresenter.setMonth(inYear, inMo, inMonth);
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