package h.khall.client.ui.event;

import h.style.g.client.ui.event.Event;
import h.style.g.client.ui.event.EventHandler;
import h.style.g.client.ui.event.TypeH;

public class PersonInfoEvent extends Event<PersonInfoEvent.Handler>
{
  public static final TypeH<Handler> TYPE = new TypeH<>(PersonInfoEvent.class);

  public interface Handler extends EventHandler
  {
    void dispatch(PersonInfoEvent inEvent);
  }

  private Long mId;

  public PersonInfoEvent(Long inId)
  {
    mId = inId;
  }

  public Long getId()
  {
    return mId;
  }

  @Override
  public Type<Handler> getAssociatedType()
  {
    return TYPE;
  }

  @Override
  protected void dispatch(Handler inHandler)
  {
    debug(inHandler);
    inHandler.dispatch(this);
  }
}