package h.khall.client.model;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasText;

import h.khall.client.ui.event.ParticipantInfoEvent;
import h.khall.shared.command.PersonSaveCommand;
import h.model.shared.khall.HasGLabel;
import h.model.shared.khall.Person;
import h.model.shared.util.EnumUtil;

public abstract class HasGLabelPresenter<E extends Enum<?> & HasGLabel>
  extends AbstractPresenter<HasGLabelPresenter.Display>
  implements ParticipantInfoEvent.Handler, ClickHandler
{
  private Long mId;

  public HasGLabelPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
    mDisplay.getLabel().setText(name());
    for (E value : values())
    {
      mDisplay.addOption(value.gLabel(), value.name(), this);
    }
  }

  abstract String name();

  abstract E[] values();

  abstract List<E> values(Person inPerson);

  public HasGLabelPresenter<E> handlers()
  {
    register(addHandler(ParticipantInfoEvent.TYPE, this));
    return this;
  }

  @Override
  public void onClick(ClickEvent inEvent)
  {
    if (mId != null)
    {
      String[] option = mDisplay.gOption(inEvent);
      Person person = mClient.gPerson(mId);
      update(Boolean.valueOf(option[1]), values(person), EnumUtil.valueOf(option[0], values()));
      fire(new PersonSaveCommand(person));
    }
    else
    {
      mDisplay.undo(inEvent);
      mDisplay.notify("Select someone first.");
    }
  }

  @Override
  public void dispatch(ParticipantInfoEvent inEvent)
  {
    mId = inEvent.getParticipantId();
    mDisplay.setOptions(values(mClient.gPerson(mId)));
  }

  public interface Display extends h.style.g.client.model.Display
  {
    <T extends Enum<?>> void setOptions(List<T> inList);

    void undo(ClickEvent inEvent);

    String[] gOption(ClickEvent inEvent);

    void addOption(String inLabel, String inId, ClickHandler inHandler);

    HasText getLabel();
  }
}