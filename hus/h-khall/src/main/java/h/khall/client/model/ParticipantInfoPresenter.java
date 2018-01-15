package h.khall.client.model;

import java.util.List;

import h.khall.client.ui.event.ParticipantInfoEvent;
import h.khall.shared.command.PersonSaveCommand;
import h.model.shared.khall.Categories.Category;
import h.model.shared.khall.Hall;
import h.model.shared.khall.Part;
import h.model.shared.khall.Person;
import h.model.shared.khall.Roles.Role;

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
    mDisplay.check(p.getParts(), p.getHalls(), p.getRoles().getRoles(),
        p.getCategories().getCategories());
  }

  public void check(Hall inHall, Boolean inChecked)
  {
    if (mParticipantId != null)
    {
      Person person = mClient.gPerson(mParticipantId);
      check(inChecked, person.getHalls(), inHall);
      fire(new PersonSaveCommand(person));
    }
  }

  public void check(Part inPart, Boolean inChecked)
  {
    if (mParticipantId != null)
    {
      Person person = mClient.gPerson(mParticipantId);
      check(inChecked, person.getParts(), inPart);
      fire(new PersonSaveCommand(person));
    }
  }

  public void check(Role inRole, Boolean inChecked)
  {
    if (mParticipantId != null)
    {
      Person person = mClient.gPerson(mParticipantId);
      check(inChecked, person.getRoles().getRoles(), inRole);
      fire(new PersonSaveCommand(person));
    }
  }

  public void check(Category inCategory, Boolean inChecked)
  {
    if (mParticipantId != null)
    {
      Person person = mClient.gPerson(mParticipantId);
      check(inChecked, person.getCategories().getCategories(), inCategory);
      fire(new PersonSaveCommand(person));
    }
  }

  private <T extends Enum<?>> void check(Boolean inChecked, List<T> inList, T inValue)
  {
    if (inChecked)
    {
      if (!inList.contains(inValue))
      {
        inList.add(inValue);
      }
    }
    else
    {
      inList.remove(inValue);
    }
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void setName(String inName);

    void check(List<Part> inParts, List<Hall> inHalls, List<Role> inRoles,
        List<Category> inCategories);
  }
}