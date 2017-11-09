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

import h.model.shared.Person;

public class PersonMultiView extends h.style.g.client.ui.AbstractView
  implements ItemTextCallback<Person>, ItemValueCallback<Person>, ItemAddedHandler<Person>,
  ItemRemovedHandler<Person>, OnTagExistsCallback<Person>, ScheduledCommand
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PersonMultiView>
  {
  }

  @UiField
  MultiValueTagsInput<Person> mTag;

  public PersonMultiView()
  {
    initWidget(BINDER.createAndBindUi(this));

    mTag.setItemText(this);
    mTag.setItemValue(this);
    mTag.onTagExists(this);

    mTag.setDatasets(new PersonSet());

    Scheduler.get().scheduleDeferred(this);
  }

  public void setValue(List<Person> inValue)
  {
    for (Person value : inValue)
    {
      mTag.add(value);
    }
  }

  @Override
  public void execute()
  {
    List<Person> persons = new ArrayList<>();
    persons.add(new Person("Hearring", "Simeon"));
    // persons.add(new Person("Hearring", "Nadia"));
    setValue(persons);
    mTag.addItemAddedHandler(this);
    mTag.addItemRemovedHandler(this);
  }

  public void clear()
  {
    mTag.removeAll();
  }

  @Override
  public void onTagExists(Person inPerson, Element inTag)
  {
    notify(inPerson.getName() + " exists.");
  }

  @Override
  public void onItemAdded(ItemAddedEvent<Person> inEvent)
  {
    notify(inEvent.getItem().getName() + " added.");
  }

  @Override
  public void onItemRemoved(ItemRemovedEvent<Person> inEvent)
  {
    notify(inEvent.getItem().getName() + " removed.");
  }

  @Override
  public String getItemValue(Person inItem)
  {
    return inItem.getName();
  }

  @Override
  public String getItemText(Person inItem)
  {
    return inItem.getName();
  }
}