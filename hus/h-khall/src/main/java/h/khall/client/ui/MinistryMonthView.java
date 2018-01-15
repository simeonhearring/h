package h.khall.client.ui;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Input;
import org.gwtbootstrap3.client.ui.Label;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.MinistryMonthPresenter;
import h.model.shared.khall.Report;
import h.model.shared.khall.Roles.Role;
import h.style.g.client.model.InputDisplay;
import h.style.g.client.ui.InputView;
import h.style.g.client.ui.NumberView;

public class MinistryMonthView extends AbstractView<MinistryMonthPresenter>
  implements MinistryMonthPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, MinistryMonthView>
  {
  }

  @UiField
  NumberView mPlacement, mVideo, mRv, mStudy;

  @UiField
  HoursView mHour, mOther;

  @UiField
  InputView mComment;

  @UiField
  TakesPartial mPartial;

  @UiField
  TakesPioneer mPioneer;

  @UiField
  Input mSend;

  @UiField
  Button mSave;

  @UiField
  Label mMonth;

  private TakesDate mSendV;

  public MinistryMonthView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new MinistryMonthPresenter(this).handlers();
    mSendV = new TakesDate(mSend);
  }

  @UiHandler(
  {
      "mSave"
  })
  public void onClick(ClickEvent inEvent)
  {
    mPresenter.save();
  }

  @Override
  public InputDisplay<Integer> getPlacement()
  {
    return mPlacement;
  }

  @Override
  public InputDisplay<Integer> getVideo()
  {
    return mVideo;
  }

  @Override
  public HasText getMonth()
  {
    return mMonth;
  }

  public Label getLabel()
  {
    return mMonth;
  }

  public HoursView getHours()
  {
    return mHour;
  }

  @Override
  public InputDisplay<Integer> getHour()
  {
    return mHour.getBox();
  }

  @Override
  public InputDisplay<Integer> getRv()
  {
    return mRv;
  }

  @Override
  public InputDisplay<Integer> getStudy()
  {
    return mStudy;
  }

  @Override
  public InputDisplay<String> getComment()
  {
    return mComment;
  }

  @Override
  public TakesValue<Boolean> getNoActivity()
  {
    return mHour.getCheck();
  }

  @Override
  public TakesValue<Date> getSend()
  {
    return mSendV;
  }

  @Override
  public TakesValue<Double> getPartial()
  {
    return mPartial;
  }

  @Override
  public InputDisplay<Integer> getCredit()
  {
    return mOther.getBox();
  }

  @Override
  public TakesValue<Boolean> getInclude()
  {
    return mOther.getCheck();
  }

  @Override
  public TakesValue<Role> getPioneer()
  {
    return mPioneer;
  }

  @Override
  public void changePub(Long inPubId, int[] inYearMonth)
  {
    mPresenter.changePub(inPubId, inYearMonth);
  }

  @Override
  public void changeMonth(int[] inYearMonth)
  {
    mPresenter.changeMonth(inYearMonth);
  }

  @Override
  public void save()
  {
    mPresenter.save();
  }

  @Override
  public void report(Report inReport)
  {
    mPresenter.report(inReport);
  }

  @Override
  public void display(Report inReport)
  {
    mPresenter.display(inReport);
  }

  public void addChangeHandler()
  {
    mPlacement.addChangeHandler(mPresenter);
    mVideo.addChangeHandler(mPresenter);
    mRv.addChangeHandler(mPresenter);
    mStudy.addChangeHandler(mPresenter);
    mHour.addChangeHandler(mPresenter);
    mOther.addChangeHandler(mPresenter);
    mPartial.addChangeHandler(mPresenter);
    mPioneer.addChangeHandler(mPresenter);
    mSend.addChangeHandler(mPresenter);
    mComment.addChangeHandler(mPresenter);
  }

  @Override
  public boolean isDirty()
  {
    return mPresenter.isDirty();
  }

  @Override
  public int[] getYearMonth()
  {
    return mPresenter.getYearMonth();
  }

  @Override
  public void setDirty(boolean inDirty)
  {
    mPresenter.setDirty(inDirty);
  }

  @Override
  public void setYearMonth(int... inYearMonth)
  {
    mPresenter.setYearMonth(inYearMonth);
  }
}