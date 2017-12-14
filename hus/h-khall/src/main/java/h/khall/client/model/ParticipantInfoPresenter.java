package h.khall.client.model;

import java.util.List;

import h.khall.client.ui.event.ParticipantInfoEvent;
import h.khall.shared.command.PersonSaveCommand;
import h.model.shared.khall.Part;
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
    mDisplay.check(p.getParts());
  }

  public void clicked(Part inPart, Boolean inValue)
  {
    if (mParticipantId != null)
    {
      Person person = mClient.gPerson(mParticipantId);
      List<Part> parts = person.getParts();

      if (inValue)
      {
        if (!parts.contains(inPart))
        {
          parts.add(inPart);
        }
      }
      else
      {
        parts.remove(inPart);
      }

      fire(new PersonSaveCommand(person));
    }
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void setName(String inName);

    void check(List<Part> inParts);
  }
}