package h.khall.client.model;

import java.util.Map.Entry;

import org.gwtbootstrap3.extras.bootbox.client.callback.ConfirmCallback;

import h.khall.client.ui.event.PersonInfoEvent;
import h.khall.shared.command.PersonSaveCommand;
import h.model.shared.khall.FieldServiceGroup;
import h.model.shared.khall.Person;
import h.style.g.client.ui.event.RefreshEvent;

public class FsgPresenter extends AbstractPresenter<FsgPresenter.Display>
  implements PersonInfoEvent.Handler, RefreshEvent.Handler
{
  private Long mId;

  public FsgPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public FsgPresenter handlers()
  {
    register(addHandler(PersonInfoEvent.TYPE, this));
    register(addHandler(RefreshEvent.TYPE, this));
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
  }

  @Override
  public void dispatch(PersonInfoEvent inEvent)
  {
    mId = inEvent.getId();
    mDisplay.set(mClient.gPerson(mId).getFsgId());
  }

  public void change(final String inValue)
  {
    if (mId != null)
    {
      if ("0".equals(inValue))
      {
        String msg = "Are you sure you want to remove " + mClient.gName(mId)
            + " from the congregation? Doing so will also remove roles.";
        mDisplay.confirm(msg, new ConfirmCallback()
        {
          @Override
          public void callback(boolean inResult)
          {
            if (inResult)
            {
              Person person = mClient.gPerson(mId);
              person.moveout();
              person.setFsgId(Integer.valueOf(inValue));
              fire(new PersonSaveCommand(person), new PersonInfoEvent(mId));
            }
          }
        });
      }
      else
      {
        Person person = mClient.gPerson(mId);
        person.setFsgId(Integer.valueOf(inValue));
        fire(new PersonSaveCommand(person), new PersonInfoEvent(mId));
      }
    }
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void add(String inItem, Integer inValue);

    void set(Integer inValue);

    void clear();
  }
}