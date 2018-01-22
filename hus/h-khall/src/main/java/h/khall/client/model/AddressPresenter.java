package h.khall.client.model;

import h.khall.client.ui.event.PersonInfoEvent;
import h.khall.shared.command.PersonSaveCommand;
import h.model.shared.khall.Person;
import h.model.shared.khall.Profile.Security;

public class AddressPresenter extends AbstractPresenter<AddressPresenter.Display>
  implements PersonInfoEvent.Handler
{
  public AddressPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public AddressPresenter handlers()
  {
    register(addHandler(PersonInfoEvent.TYPE, this));
    return this;
  }

  @Override
  public void dispatch(PersonInfoEvent inEvent)
  {
    mDisplay.setPerson(mClient.gPerson(inEvent.getId()));
    mDisplay.editDetail(mProfile.isEdit(Security.PERSON_DETAIL));
    mDisplay.editContactInfo(mProfile.isEdit(Security.PERSON_CONTACT_INFO));
  }

  public void chgAddress1(long inId, String inText)
  {
    Person p = mClient.gPerson(inId);
    p.setAddress1(inText);
    save(p);
  }

  public void chgAddress2(long inId, String inText)
  {
    Person p = mClient.gPerson(inId);
    p.setAddress2(inText);
    save(p);
  }

  public void chgCity(long inId, String inText)
  {
    Person p = mClient.gPerson(inId);
    p.setCity(inText);
    save(p);
  }

  public void chgState(long inId, String inText)
  {
    Person p = mClient.gPerson(inId);
    p.setState(inText);
    save(p);
  }

  public void chgZip(long inId, String inText)
  {
    Person p = mClient.gPerson(inId);
    p.setZip(inText);
    save(p);
  }

  public void chgHome(long inId, String inText)
  {
    Person p = mClient.gPerson(inId);
    p.setHome(inText);
    save(p);
  }

  public void chgMobile(long inId, String inText)
  {
    Person p = mClient.gPerson(inId);
    p.setMobile(inText);
    save(p);
  }

  public void chgEmail(long inId, String inText)
  {
    Person p = mClient.gPerson(inId);
    p.setEmail(inText);
    save(p);
  }

  public void chgFirst(long inId, String inText)
  {
    Person p = mClient.gPerson(inId);
    p.setFirst(inText);
    save(p);
  }

  public void chgLast(long inId, String inText)
  {
    Person p = mClient.gPerson(inId);
    p.setLast(inText);
    save(p);
  }

  public void chgMiddle(long inId, String inText)
  {
    Person p = mClient.gPerson(inId);
    p.setMiddle(inText);
    save(p);
  }

  public void chgSuffix(long inId, String inText)
  {
    Person p = mClient.gPerson(inId);
    p.setSuffix(inText);
    save(p);
  }

  private void save(Person inPerson)
  {
    fire(new PersonSaveCommand(inPerson), new PersonInfoEvent(inPerson.getIdLong()));
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void setPerson(Person inPerson);

    void editDetail(boolean inEnable);

    void editContactInfo(boolean inEnable);
  }
}