package h.khall.client.model;

import h.khall.client.ui.event.ParticipantInfoEvent;

public class ParticipantInfoPresenter extends AbstractPresenter<ParticipantInfoPresenter.Display>
  implements ParticipantInfoEvent.Handler
{
  private Long mId;

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
    mId = inEvent.getParticipantId();
    mDisplay.setName(mClient.gPerson(mId).getName());
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void setName(String inName);

    void showRole(boolean inVisible);

    void showCategory(boolean inVisible);

    void showPart(boolean inVisible);

    void showHall(boolean inVisible);
  }
}