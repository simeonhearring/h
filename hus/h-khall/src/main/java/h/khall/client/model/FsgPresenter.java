package h.khall.client.model;

import java.util.Map.Entry;

import org.gwtbootstrap3.extras.bootbox.client.callback.ConfirmCallback;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.HandlerRegistration;

import h.khall.client.ui.event.PersonInfoEvent;
import h.khall.shared.command.PersonSaveCommand;
import h.model.shared.khall.FieldServiceGroup;
import h.model.shared.khall.Person;
import h.style.g.client.ui.event.RefreshEvent;

public class FsgPresenter extends AbstractPresenter<FsgPresenter.Display>
  implements PersonInfoEvent.Handler, RefreshEvent.Handler, ChangeHandler
{
  private Long mId;
  private boolean mExtra;

  public FsgPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public FsgPresenter handlers()
  {
    register(addHandler(PersonInfoEvent.TYPE, this));
    register(addHandler(RefreshEvent.TYPE, this));
    register(mDisplay.addChangeHandler(this)); // TODO should be set in all
                                               // instances. ie from
                                               // MinistryView
    return this;
  }

  @Override
  public void dispatch(RefreshEvent inEvent)
  {
    mDisplay.clear();
    mDisplay.add("-Select One-", 0);
    for (Entry<Integer, FieldServiceGroup> value : mClient.getCong().getFsgs().entrySet())
    {
      mDisplay.add(value.getValue().getTitle(), value.getKey());
    }

    if (mExtra)
    {
      mDisplay.add(FieldServiceGroup.NAMES[1], FieldServiceGroup.ID[1]);
      mDisplay.add(FieldServiceGroup.NAMES[2], FieldServiceGroup.ID[2]);
      mDisplay.add(FieldServiceGroup.NAMES[3], FieldServiceGroup.ID[3]);
    }
  }

  public void setExtra(boolean inExtra)
  {
    mExtra = inExtra;
  }

  @Override
  public void onChange(ChangeEvent inEvent)
  {
    change(mDisplay.getSelectedValue());
  }

  @Override
  public void dispatch(PersonInfoEvent inEvent)
  {
    mId = inEvent.getId();
    mDisplay.set(mClient.gPerson(mId).getFsgId());
  }

  public void change(final Integer inValue)
  {
    if (mId != null)
    {
      if (inValue.intValue() == 0)
      {
        String msg = "Are you sure you want to remove " + mClient.gName(mId)
            + " from the congregation? Doing so will remove roles.";
        mDisplay.confirm(msg, new ConfirmCallback()
        {
          @Override
          public void callback(boolean inResult)
          {
            if (inResult)
            {
              Person person = mClient.gPerson(mId);
              person.moveout();
              person.setFsgId(inValue);
              fire(new PersonSaveCommand(person), new PersonInfoEvent(mId));
            }
          }
        });
      }
      else
      {
        Person person = mClient.gPerson(mId);
        person.setFsgId(inValue);
        fire(new PersonSaveCommand(person), new PersonInfoEvent(mId));
      }
    }
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void add(String inItem, Integer inValue);

    void set(Integer inValue);

    void clear();

    Integer getSelectedValue();

    boolean isSource(Event<?> inEvent);

    HandlerRegistration addChangeHandler(ChangeHandler inHandler);

    void setExtra(boolean inExtra);
  }
}