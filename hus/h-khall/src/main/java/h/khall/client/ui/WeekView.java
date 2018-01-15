package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.gwt.FlowPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.WeekPresenter;
import h.model.shared.khall.Meeting.Week;

public class WeekView extends AbstractView<WeekPresenter> implements WeekPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, WeekView>
  {
  }

  @UiField
  HeadingElement mWeekOf;

  @UiField
  FlowPanel mEvents;

  @UiField
  AssignView mChairman, mPrayer1, mPrayer2, mTreasures, mDigging, mLiving1, mLiving2, mCStudy,
      mReadingM, mApply1M, mApply2M, mApply3M, mReadingA, mApply1A, mApply2A, mApply3A;

  public WeekView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new WeekPresenter(this);
  }

  @Override
  public void addEvent(String inDisplay)
  {
    mEvents.add(new InlineLabel(inDisplay));
  }

  @Override
  public void clearEvents()
  {
    mEvents.clear();
  }

  @Override
  public void reset()
  {
    mPresenter.reset();
  }

  @Override
  public void setWeek(Week inWeek)
  {
    mPresenter.setWeek(inWeek);
  }

  @Override
  public void setWeekOf(String inText)
  {
    mWeekOf.setInnerText(inText);
  }

  @Override
  public WeekPresenter.AssignDisplay[] getAssignDisplay()
  {
    return new WeekPresenter.AssignDisplay[]
    {
        mChairman,
        mPrayer1,
        mPrayer2,
        mTreasures,
        mDigging,
        mLiving1,
        mLiving2,
        mCStudy,

        mReadingM,
        mApply1M,
        mApply2M,
        mApply3M,

        mReadingA,
        mApply1A,
        mApply2A,
        mApply3A
    };
  }
}