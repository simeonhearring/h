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

import h.khall.client.model.AssignmentPresenter;
import h.khall.client.model.AssignmentPresenter.AssignDisplay;
import h.khall.shared.model.Hall;
import h.khall.shared.model.Part;
import h.model.shared.Tag;
import h.style.g.client.model.CallBack;

public class AssignView extends h.style.g.client.ui.AbstractView
  implements ItemTextCallback<Tag>, ItemValueCallback<Tag>, ItemAddedHandler<Tag>,
  ItemRemovedHandler<Tag>, OnTagExistsCallback<Tag>, AssignmentPresenter.AssignDisplay
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
  private Part mPart;
  private AssignSet mDataset;
  private CallBack<AssignDisplay> mAddedCallBack;

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

  public void setLabel(String inText)
  {
    mLabel.setInnerText(inText);
  }

  public void setColor(String inColor)
  {
    mLabel.getStyle().setColor(inColor);
  }

  public void setPart(Part inPart)
  {
    mPart = inPart;
    setLabel(mPart.getLabel(true));
    if (mDataset != null)
    {
      mDataset.setPart(mPart);
    }
  }

  @Override
  public void setValue(List<Tag> inValue)
  {
    clear();
    for (Tag value : inValue)
    {
      mTag.add(value);
    }
  }

  @Override
  public void setValue(Tag... inValue)
  {
    clear();
    for (Tag value : inValue)
    {
      mTag.add(value);
    }
  }

  public void clear()
  {
    mTag.removeAll();
  }

  @Override
  public void onTagExists(Tag inPerson, Element inTag)
  {
    notify(inPerson.getName() + " exists.");
  }

  @Override
  public void onItemAdded(ItemAddedEvent<Tag> inEvent)
  {
    if (mAddedCallBack != null)
    {
      mAddedCallBack.onCallBack(this);
    }
  }

  @Override
  public void onItemRemoved(ItemRemovedEvent<Tag> inEvent)
  {
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
  public void setAddedCallback(CallBack<AssignDisplay> inCallBack)
  {
    mAddedCallBack = inCallBack;
  }
}