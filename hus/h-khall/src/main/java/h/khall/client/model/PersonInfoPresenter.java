package h.khall.client.model;

import h.khall.client.ui.event.PersonInfoEvent;

public class PersonInfoPresenter extends AbstractPresenter<PersonInfoPresenter.Display>
  implements PersonInfoEvent.Handler
{
  private Long mId;

  public PersonInfoPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public PersonInfoPresenter handlers()
  {
    register(addHandler(PersonInfoEvent.TYPE, this));
    return this;
  }

  @Override
  public void dispatch(PersonInfoEvent inEvent)
  {
    mId = inEvent.getId();
    mDisplay.setName(mClient.gPerson(mId).getName());
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void setName(String inName);

    void setRoleVisible(boolean inVisible);

    void setCategoryVisible(boolean inVisible);

    void setPartVisible(boolean inVisible);

    void setHallVisible(boolean inVisible);

    void setFsgVisible(boolean inVisible);
  }
}