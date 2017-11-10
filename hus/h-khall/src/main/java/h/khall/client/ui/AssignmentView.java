package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.AssignmentPresenter;
import h.khall.client.model.AssignmentPresenter.AssignDisplay;

public class AssignmentView extends AbstractView<AssignmentPresenter>
  implements AssignmentPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, AssignmentView>
  {
  }

  @UiField
  HeadingElement mWeekOf;

  @UiField
  AssignView mChairman, mPrayer1, mPrayer2, mTreasures, mDigging, mLiving1, mLiving2, mCStudy,
      mReading1, mInitial1, mRv1, mTalk1, mReading2, mInitial2, mRv2, mTalk2;

  public AssignmentView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new AssignmentPresenter(this).handlers();
  }

  @Override
  public void setWeekOf(String inText)
  {
    mWeekOf.setInnerText(inText);
  }

  @Override
  public AssignDisplay[] getAssignDisplay()
  {
    return new AssignDisplay[]
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