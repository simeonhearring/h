package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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
  AssignView mChairman, mPrayer1, mPrayer2, mTreasures, mDigging, mLiving1, mLiving2, mCStudy,
      mReading1, mInitial1, mRv1, mTalk1, mReading2, mInitial2, mRv2, mTalk2;

  public WeekView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new WeekPresenter(this);
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

        mReading1,
        mInitial1,
        mRv1,
        mTalk1,

        mReading2,
        mInitial2,
        mRv2,
        mTalk2
    };
  }
}