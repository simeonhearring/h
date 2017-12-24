package h.khall.client.ui;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.CheckBox;
import org.gwtbootstrap3.client.ui.Input;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.extras.tagsinput.client.callback.ItemTextCallback;
import org.gwtbootstrap3.extras.tagsinput.client.callback.ItemValueCallback;
import org.gwtbootstrap3.extras.tagsinput.client.callback.OnTagExistsCallback;
import org.gwtbootstrap3.extras.tagsinput.client.event.ItemAddedEvent;
import org.gwtbootstrap3.extras.tagsinput.client.event.ItemAddedHandler;
import org.gwtbootstrap3.extras.tagsinput.client.event.ItemRemovedEvent;
import org.gwtbootstrap3.extras.tagsinput.client.event.ItemRemovedHandler;
import org.gwtbootstrap3.extras.tagsinput.client.ui.base.MultiValueTagsInput;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.MinistryPresenter;
import h.model.shared.Tag;
import h.model.shared.khall.Roles.Role;
import h.model.shared.util.TimeUtil;
import h.style.g.client.model.CallBack;
import h.style.g.client.model.InputDisplay;
import h.style.g.client.ui.InputView;
import h.style.g.client.ui.NumberView;

public class MinistryView extends AbstractView<MinistryPresenter>
  implements ItemTextCallback<Tag>, ItemValueCallback<Tag>, ItemAddedHandler<Tag>,
  ItemRemovedHandler<Tag>, OnTagExistsCallback<Tag>, MinistryPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, MinistryView>
  {
  }

  @UiField
  MultiValueTagsInput<Tag> mName;

  @UiField
  NumberView mPlacement, mVideo, mHour, mRv, mStudy, mCredit;

  @UiField
  CheckBox mNoActivity, mInclude;

  @UiField
  InputView mComment;

  @UiField
  ListBox mPartial, mPioneer;

  @UiField
  Input mSend, mMonth;

  @UiField
  Button mSave;

  private PersonSet mDataset;
  private CallBack<Tag> mCallBack;
  private TakesDate mMonthV, mSendV;
  private TakesPartial mPartialV;
  private TakesEnum<Role> mPioneerV;

  public MinistryView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new MinistryPresenter(this).handlers();

    mMonthV = new TakesDate(mMonth);
    mSendV = new TakesDate(mSend);
    mPartialV = new TakesPartial(mPartial);

    mMonthV.setValue(TimeUtil.getFirstOfMonth(TimeUtil.currentYear(), TimeUtil.currentServiceMonth()));

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

    mDataset = new PersonSet();

    mName.setItemText(this);
    mName.setItemValue(this);
    mName.onTagExists(this);

    mName.addItemAddedHandler(this);
    mName.addItemRemovedHandler(this);

    mName.setDatasets(mDataset);
  }

  @UiHandler(
  {
      "mSave"
  })
  public void onClick(ClickEvent inEvent)
  {
    mPresenter.save();
  }

  @UiHandler(
  {
      "mMonth"
  })
  public void onChange(ChangeEvent inEvent)
  {
    mPresenter.changeMonth();
  }

  @Override
  public TakesValue<Date> getMonth()
  {
    return mMonthV;
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
  public void onTagExists(Tag inPerson, Element inTag)
  {
    notify(inPerson.getName() + " exists, no duplicates.");
  }

  @Override
  public void onItemAdded(ItemAddedEvent<Tag> inEvent)
  {
    if (mCallBack != null)
    {
      mCallBack.onCallBack(inEvent.getItem());
    }
  }

  @Override
  public void onItemRemoved(ItemRemovedEvent<Tag> inEvent)
  {
    if (mCallBack != null)
    {
      mCallBack.onCallBack(null);
    }
  }

  @Override
  public void setCallback(CallBack<Tag> inCallBack)
  {
    mCallBack = inCallBack;
  }

  @Override
  public String getItemValue(Tag inItem)
  {
    return inItem.getValue();
  }

  @Override
  public String getItemText(Tag inItem)
  {
    return inItem.getName();
  }
}