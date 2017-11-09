package h.khall.client.ui;

import java.util.ArrayList;
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
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.model.shared.Part;
import h.model.shared.Person;
import h.model.shared.StudyPoint;
import h.model.shared.Tag;

public class PersonMultiView extends h.style.g.client.ui.AbstractView
  implements ItemTextCallback<Tag>, ItemValueCallback<Tag>, ItemAddedHandler<Tag>,
  ItemRemovedHandler<Tag>, OnTagExistsCallback<Tag>, ScheduledCommand
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PersonMultiView>
  {
  }

  @UiField
  MultiValueTagsInput<Tag> mTag;

  private AssignSet mDataset;

  public PersonMultiView()
  {
    initWidget(BINDER.createAndBindUi(this));

    mDataset = new AssignSet();
    mDataset.setPart(Part.BIBLE_READING);

    mTag.setItemText(this);
    mTag.setItemValue(this);
    mTag.onTagExists(this);

    mTag.setDatasets(mDataset);

    Scheduler.get().scheduleDeferred(this);
  }

  public void setValue(List<Tag> inValue)
  {
    for (Tag value : inValue)
    {
      mTag.add(value);
    }
  }

  @Override
  public void execute()
  {
    List<Tag> values = new ArrayList<>();
    values.add(new Person("Hearring", "Simeon"));
    values.add(StudyPoint.SP_1);
    setValue(values);
    mTag.addItemAddedHandler(this);
    mTag.addItemRemovedHandler(this);
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
    notify(inEvent.getItem().getName() + " added.");
  }

  @Override
  public void onItemRemoved(ItemRemovedEvent<Tag> inEvent)
  {
    notify(inEvent.getItem().getName() + " removed.");
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