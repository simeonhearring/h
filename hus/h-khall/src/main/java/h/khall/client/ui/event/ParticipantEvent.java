package h.khall.client.ui.event;

import h.style.g.client.ui.event.Event;
import h.style.g.client.ui.event.EventHandler;
import h.style.g.client.ui.event.TypeH;

public class ParticipantEvent extends Event<ParticipantEvent.Handler>
{
  public static final TypeH<Handler> TYPE = new TypeH<>(ParticipantEvent.class);

  public interface Handler extends EventHandler
  {
    void dispatch(ParticipantEvent inEvent);
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