package h.khall.client.model;

import h.khall.client.ui.event.ParticipantInfoEvent;
import h.model.shared.khall.Person;

public class ParticipantInfoPresenter extends AbstractPresenter<ParticipantInfoPresenter.Display>
  implements ParticipantInfoEvent.Handler
{
  private Long mParticipantId;

  public ParticipantInfoPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public ParticipantInfoPresenter handlers()
  {
    register(addHandler(ParticipantInfoEvent.TYPE, this));
    return this;
  }

  @Override
  public void dispatch(ParticipantInfoEvent inEvent)
  {
    mParticipantId = inEvent.getParticipantId();
    Person p = mClient.getPersons().gPerson(mParticipantId);
    mDisplay.setName(p.getName());
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void setName(String inName);
  }
}