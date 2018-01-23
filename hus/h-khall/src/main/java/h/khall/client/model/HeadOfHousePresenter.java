package h.khall.client.model;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.HandlerRegistration;

import h.khall.client.ui.event.PersonInfoEvent;
import h.khall.shared.command.PersonSaveCommand;
import h.model.shared.khall.Person;
import h.model.shared.khall.Profile.Security;
import h.model.shared.util.NumberUtil;
import h.style.g.client.ui.event.RefreshEvent;

public class HeadOfHousePresenter extends AbstractPresenter<HeadOfHousePresenter.Display>
  implements PersonInfoEvent.Handler, RefreshEvent.Handler, ChangeHandler
{
  private Long mId;

  public HeadOfHousePresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public HeadOfHousePresenter handlers()
  {
    register(addHandler(PersonInfoEvent.TYPE, this));
    register(addHandler(RefreshEvent.TYPE, this));
    register(mDisplay.addChangeHandler(this));
    return this;
  }

  @Override
  public void dispatch(RefreshEvent inEvent)
  {
    mDisplay.editHoh(isEdit(Security.PERSON_DETAIL));
    mDisplay.clear();
    mDisplay.add("-Select One-", 0L);
    for (Person value : mClient.getPersons().getPersons())
    {
      mDisplay.add(value.gName(), value.getIdLong());
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
    mDisplay.set(mClient.gPerson(mId).getHead());
  }

  public void change(Long inValue)
  {
    if (mId != null)
    {
      Person person = mClient.gPerson(mId);
      person.setHead(NumberUtil.notZero(inValue));
      fire(new PersonSaveCommand(person), new PersonInfoEvent(mId));
    }
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void add(String inItem, Long inValue);

    void set(Long inValue);

    void clear();

    Long getSelectedValue();

    boolean isSource(Event<?> inEvent);

    HandlerRegistration addChangeHandler(ChangeHandler inHandler);

    void editHoh(boolean inEnable);
  }
}