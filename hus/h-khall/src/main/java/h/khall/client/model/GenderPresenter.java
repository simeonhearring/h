package h.khall.client.model;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.HandlerRegistration;

import h.khall.client.ui.event.PersonInfoEvent;
import h.khall.shared.command.PersonSaveCommand;
import h.model.shared.Person.Gender;
import h.model.shared.khall.Person;
import h.model.shared.khall.Profile.Security;
import h.model.shared.util.EnumUtil;
import h.style.g.client.ui.event.RefreshEvent;

public class GenderPresenter extends AbstractPresenter<GenderPresenter.Display>
  implements PersonInfoEvent.Handler, RefreshEvent.Handler, ChangeHandler
{
  private Long mId;

  public GenderPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public GenderPresenter handlers()
  {
    register(addHandler(PersonInfoEvent.TYPE, this));
    register(addHandler(RefreshEvent.TYPE, this));
    register(mDisplay.addChangeHandler(this));
    return this;
  }

  @Override
  public void dispatch(RefreshEvent inEvent)
  {
    mDisplay.editGender(isEdit(Security.PERSON_DETAIL));
    mDisplay.clear();
    for (Gender value : Gender.values())
    {
      mDisplay.add(value.gLabel(), value.name());
    }
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
    mDisplay.set(mClient.gPerson(mId).getGender().name());
  }

  public void change(String inValue)
  {
    if (mId != null)
    {
      Person person = mClient.gPerson(mId);
      person.setGender(EnumUtil.valueOf(inValue, Gender.values()));
      fire(new PersonSaveCommand(person), new PersonInfoEvent(mId));
    }
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void add(String inItem, String inValue);

    void set(String inValue);

    void clear();

    String getSelectedValue();

    boolean isSource(Event<?> inEvent);

    HandlerRegistration addChangeHandler(ChangeHandler inHandler);

    void editGender(boolean inEnable);
  }
}