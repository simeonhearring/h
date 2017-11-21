package h.khall.client.model;

import java.util.Date;

import h.khall.shared.command.SavePersonCommand;
import h.model.shared.Person.Gender;
import h.model.shared.khall.Person;
import h.style.g.client.model.AbstractPresenter;

public class PersonPresenter extends AbstractPresenter<PersonPresenter.Display>
{
  private Person mPerson;

  public PersonPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
    mDisplay.reset();
  }

  public void setPerson(Person inPerson)
  {
    mPerson = inPerson;

    mDisplay.setFirst(mPerson.getFirst());
    mDisplay.setMiddle(mPerson.getMiddle());
    mDisplay.setLast(mPerson.getLast());
    mDisplay.setSuffix(mPerson.getSuffix());
    mDisplay.setGender(mPerson.getGender());
    mDisplay.setBirth(mPerson.getBirth());
    mDisplay.setEmail(mPerson.getEmail());
    mDisplay.setMobile(mPerson.getMobile());
    mDisplay.setHome(mPerson.getHome());

    mDisplay.setAddress1(mPerson.getAddress1());
    mDisplay.setAddress2(mPerson.getAddress2());
    mDisplay.setCity(mPerson.getCity());
    mDisplay.setState(mPerson.getState());
    mDisplay.setZip(mPerson.getZip());

    // mDisplay.setFsg(mPerson.getFsgId());
    // mDisplay.setFaith(mPerson.getFaith());
    mDisplay.setBaptized(mPerson.getBaptized());
    mDisplay.setPublishing(mPerson.getPublishing());
    // TODO mDisplay.setRoles(mPerson.getRoles());
    // mDisplay.setCategories(mPerson.getCategories());
    mDisplay.setEmergency(mPerson.getEmergency());
    mDisplay.setChildren(mPerson.getChildren());
    mDisplay.setHead(mPerson.getHead());
    mDisplay.setFamily(mPerson.getFamily());
  }

  public void save()
  {
    fire(new SavePersonCommand(mPerson));
  }

  public void chgFirst(String inValue)
  {
    mPerson.setFirst(inValue);
  }

  public void chgSuffix(String inValue)
  {
    mPerson.setSuffix(inValue);
  }

  public void chgEmail(String inValue)
  {
    mPerson.setEmail(inValue);
  }

  public void chgMiddle(String inValue)
  {
    mPerson.setMiddle(inValue);
  }

  public void chgGender(Gender inValue)
  {
    mPerson.setGender(inValue);
  }

  public void chgMobile(String inValue)
  {
    mPerson.setMobile(inValue);
  }

  public void chgLast(String inValue)
  {
    mPerson.setLast(inValue);
  }

  public void chgBirth(Date inValue)
  {
    mPerson.setBirth(inValue);
  }

  public void chgHome(String inValue)
  {
    mPerson.setHome(inValue);
  }

  public void chgAddress1(String inValue)
  {
    mPerson.setAddress1(inValue);
  }

  public void chgCity(String inValue)
  {
    mPerson.setCity(inValue);
  }

  public void chgAddress2(String inValue)
  {
    mPerson.setAddress2(inValue);
  }

  public void chgState(String inValue)
  {
    mPerson.setState(inValue);
  }

  public void chgZip(String inValue)
  {
    mPerson.setZip(inValue);
  }

  public void chgFsg(String inValue)
  {
    // mPerson.setFsgId(inValue);
  }

  public void chgBaptized(Date inValue)
  {
    mPerson.setBaptized(inValue);
  }

  public void chgTypes(String inValue)
  {
    // mPerson.setRoles(inValue);
  }

  public void chgEmergency(String inValue)
  {
    mPerson.setEmergency(inValue);
  }

  public void chgHead(String inValue)
  {
    mPerson.setHead(inValue);
  }

  public void chgFaith(String inValue)
  {
    // mPerson.setFaith(inValue);
  }

  public void chgPublishing(Date inValue)
  {
    mPerson.setPublishing(inValue);
  }

  public void chgCategories(String inValue)
  {
    // mPerson.setCategories(inValue);
  }

  public void chgChildren(String inValue)
  {
    mPerson.setChildren(inValue);
  }

  public void chgFamily(String inValue)
  {
    mPerson.setFamily(inValue);
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void setFirst(String inValue);

    void reset();

    void setMiddle(String inValue);

    void setLast(String inValue);

    void setSuffix(String inValue);

    void setGender(Gender inValue);

    void setBirth(Date inValue);

    void setEmail(String inValue);

    void setMobile(String inValue);

    void setHome(String inValue);

    void setAddress1(String inValue);

    void setAddress2(String inValue);

    void setCity(String inValue);

    void setState(String inValue);

    void setZip(String inValue);

    void setFsg(String inValue);

    void setFaith(String inValue);

    void setBaptized(Date inValue);

    void setPublishing(Date inValue);

    void setRoles(String inValue);

    void setCategories(String inValue);

    void setEmergency(String inValue);

    void setChildren(String inValue);

    void setHead(String inValue);

    void setFamily(String inValue);
  }
}