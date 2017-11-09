package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.AssignmentPresenter;
import h.model.shared.Part;
import h.model.shared.Person;

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
    mPresenter = new AssignmentPresenter(this);

    mWeekOf.setInnerText("November 20-27");

    Scheduler.get().scheduleDeferred(new ScheduledCommand()
    {
      @Override
      public void execute()
      {
        mChairman.setValue(new Person("Hearring", "mChairman"));
        mPrayer1.setValue(new Person("Hearring", "mPrayer1"));
        mPrayer2.setValue(new Person("Hearring", "mPrayer2"));
        mTreasures.setValue(new Person("Hearring", "mTreasures"));
        mDigging.setValue(new Person("Hearring", "mDigging"));
        mLiving1.setValue(new Person("Hearring", "mLiving1"));
        mLiving2.setValue(new Person("Hearring", "mLiving2"));
        mCStudy.setValue(new Person("Hearring", "mCStudy"));
        mReading1.setValue(new Person("Hearring", "mReading1"));
        mInitial1.setValue(new Person("Hearring", "mInitial1"));
        mRv1.setValue(new Person("Hearring", "mRv1"));
        mTalk1.setValue(new Person("Hearring", "mTalk1"));
        mReading2.setValue(new Person("Hearring", "mReading2"));
        mInitial2.setValue(new Person("Hearring", "mInitial2"));
        mRv2.setValue(new Person("Hearring", "mRv2"));
        mTalk2.setValue(new Person("Hearring", "mTalk2"));
        mTalk2.setPart(Part.BIBLE_STUDY);

        mChairman.setAddedCallback(mPresenter);

      }
    });
  }
}