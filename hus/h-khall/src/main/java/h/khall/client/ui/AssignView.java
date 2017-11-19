package h.khall.client.ui;

import java.util.List;

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
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.WeekPresenter;
import h.model.shared.Tag;
import h.model.shared.khall.Assignment;
import h.model.shared.khall.Hall;
import h.model.shared.khall.Part;
import h.style.g.client.model.CallBack;

public class AssignView extends h.style.g.client.ui.AbstractView
  implements ItemTextCallback<Tag>, ItemValueCallback<Tag>, ItemAddedHandler<Tag>,
  ItemRemovedHandler<Tag>, OnTagExistsCallback<Tag>, WeekPresenter.AssignDisplay
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, AssignView>
  {
  }

  @UiField
  LabelElement mLabel;

  @UiField
  MultiValueTagsInput<Tag> mTag;

  private Hall mHall = Hall.MAIN;
  private Part mPart, mPpart;
  private AssignSet mDataset;
  private CallBack<WeekPresenter.AssignDisplay> mCallBack;

  private Assignment mAssignment;

  public AssignView()
  {
    initWidget(BINDER.createAndBindUi(this));

    mDataset = new AssignSet();
    mDataset.setPart(mPart);

    mTag.setItemText(this);
    mTag.setItemValue(this);
    mTag.onTagExists(this);

    mTag.addItemAddedHandler(this);
    mTag.addItemRemovedHandler(this);

    mTag.setDatasets(mDataset);
  }

  @Override
  public Assignment getAssignment()
  {
    return mAssignment;
  }

  @Override
  public void setAssignment(Assignment inAssignment)
  {
    mAssignment = inAssignment;
  }

  public void setHall(Hall inHall)
  {
    mHall = inHall;
  }

  @Override
  public Hall getHall()
  {
    return mHall;
  }

  @Override
  public Part getPart()
  {
    return mPart;
  }

  @Override
  public List<Tag> getValues()
  {
    return mTag.getItems();
  }

  @Override
  public void remove(Tag inTag)
  {
    mTag.remove(inTag);
  }

  @Override
  public void setLabel(String inText)
  {
    mLabel.setInnerText(inText);
  }

  @Override
  public void setColor(String inColor)
  {
    mLabel.getStyle().setColor(inColor);
  }

  @Override
  public void setPart(Part inPart)
  {
    mPart = inPart;
    mPpart = mPpart == null ? mPart : mPpart;
    setLabel(mPart.getLabel(true));
    if (mDataset != null)
    {
      mDataset.setPart(mPart);
    }
  }

  public void setPpart(Part inPpart)
  {
    mPpart = inPpart;
  }

  @Override
  public Part getPpart()
  {
    return mPpart;
  }

  @Override
  public void setValue(List<Tag> inValue)
  {
    for (Tag value : inValue)
    {
      mTag.add(value);
    }
  }

  @Override
  public void setValue(Tag... inValue)
  {
    for (Tag value : inValue)
    {
      mTag.add(value);
    }
  }

  @Override
  public void clear()
  {
    mTag.removeAll();
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
      mCallBack.onCallBack(this);
    }
  }

  @Override
  public void onItemRemoved(ItemRemovedEvent<Tag> inEvent)
  {
    if (mCallBack != null)
    {
      mCallBack.onCallBack(this);
    }
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

  @Override
  public void setCallback(CallBack<WeekPresenter.AssignDisplay> inCallBack)
  {
    mCallBack = inCallBack;
  }
}