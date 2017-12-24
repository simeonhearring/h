package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.Button;
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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.MinistryPresenter;
import h.model.shared.Tag;
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
  InputView mMonth;

  @UiField
  NumberView mPlacement, mVideo, mHour, mRv, mStudy, mCredit;

  @UiField
  InputView mNoActivity, mSend, mPartial, mComment, mInclude, mPioneer;

  @UiField
  Button mSave;

  private PersonSet mDataset;
  private CallBack<Tag> mCallBack;

  public MinistryView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new MinistryPresenter(this).handlers();

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

  @Override
  public InputDisplay<String> getMonth()
  {
    return mMonth;
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
  public InputDisplay<String> getNoActivity()
  {
    return mNoActivity;
  }

  @Override
  public InputDisplay<String> getSend()
  {
    return mSend;
  }

  @Override
  public InputDisplay<String> getPartial()
  {
    return mPartial;
  }

  @Override
  public InputDisplay<Integer> getCredit()
  {
    return mCredit;
  }

  @Override
  public InputDisplay<String> getInclude()
  {
    return mInclude;
  }

  @Override
  public InputDisplay<String> getPioneer()
  {
    return mPioneer;
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