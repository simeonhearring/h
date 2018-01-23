package h.khall.client.model;

import java.util.Date;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.web.bindery.event.shared.HandlerRegistration;

import h.khall.client.ui.event.PersonInfoEvent;
import h.khall.shared.command.PersonSaveCommand;
import h.model.shared.khall.Person;
import h.style.g.client.ui.event.RefreshEvent;

public class BirthPresenter extends AbstractPresenter<BirthPresenter.Display>
  implements PersonInfoEvent.Handler, RefreshEvent.Handler, ChangeHandler
{
  private Long mId;

  public BirthPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public BirthPresenter handlers()
  {
    register(addHandler(PersonInfoEvent.TYPE, this));
    register(addHandler(RefreshEvent.TYPE, this));
    register(mDisplay.addChangeHandler(this));
    return this;
  }

  @Override
  public void dispatch(RefreshEvent inEvent)
  {
    if (mId != null)
    {
      Person person = mClient.gPerson(mId);
      mDisplay.setDate(person.getBirth());
    }
  }

  @Override
  public void onChange(ChangeEvent inEvent)
  {
    if (mId != null)
    {
      Person person = mClient.gPerson(mId);
      person.setBirth(mDisplay.getDate());
      fire(new PersonSaveCommand(person), new PersonInfoEvent(mId));
    }
  }

  @Override
  public void dispatch(PersonInfoEvent inEvent)
  {
    mId = inEvent.getId();
    Person person = mClient.gPerson(mId);
    mDisplay.setDate(person.getBirth());
  }

  public interface Display extends h.style.g.client.model.Display
  {
    Date getDate();

    void setDate(Date inDate);

    HandlerRegistration addChangeHandler(ChangeHandler inHandler);
  }
}