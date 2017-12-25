package h.khall.client.ui;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.CheckBox;
import org.gwtbootstrap3.client.ui.Input;
import org.gwtbootstrap3.client.ui.ListBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.MinistryMonthPresenter;
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
  NumberView mPlacement, mVideo, mHour, mRv, mStudy, mCredit;

  @UiField
  CheckBox mNoActivity, mInclude;

  @UiField
  InputView mComment;

  @UiField
  ListBox mPartial, mPioneer;

  @UiField
  Input mSend;

  @UiField
  Button mSave;

  private TakesDate mSendV;
  private TakesPartial mPartialV;
  private TakesEnum<Role> mPioneerV;

  public MinistryMonthView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new MinistryMonthPresenter(this).handlers();

    mSendV = new TakesDate(mSend);
    mPartialV = new TakesPartial(mPartial);

    mPartial.addItem("-Select-");
    mPartial.addItem("15 min.");
    mPartial.addItem("30 min.");
    mPartial.addItem("45 min.");

    mPioneer.addItem("-Select-", "");
    mPioneer.addItem("Auxiliary 30", Role.AUXILIARY_PIONEER_30.name());
    mPioneer.addItem("Auxiliary", Role.AUXILIARY_PIONEER.name());
    mPioneer.addItem("Regular", Role.REGULAR_PIONEER.name());

    mPioneerV = new TakesEnum<>(mPioneer);
    mPioneerV.setEnums(Role.AUXILIARY_PIONEER_30, Role.AUXILIARY_PIONEER, Role.REGULAR_PIONEER);
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
  public InputDisplay<Integer> getHour()
  {
    return mHour;
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
    return mNoActivity;
  }

  @Override
  public TakesValue<Date> getSend()
  {
    return mSendV;
  }

  @Override
  public TakesValue<Double> getPartial()
  {
    return mPartialV;
  }

  @Override
  public InputDisplay<Integer> getCredit()
  {
    return mCredit;
  }

  @Override
  public TakesValue<Boolean> getInclude()
  {
    return mInclude;
  }

  @Override
  public TakesValue<Role> getPioneer()
  {
    return mPioneerV;
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
}