package h.khall.client.ui.event;

import h.style.g.client.ui.event.Event;
import h.style.g.client.ui.event.EventHandler;
import h.style.g.client.ui.event.TypeH;

public class ResendProfileEvent extends Event<ResendProfileEvent.Handler>
{
  public static final TypeH<Handler> TYPE = new TypeH<>(ResendProfileEvent.class);

  public interface Handler extends EventHandler
  {
    void dispatch(ResendProfileEvent inEvent);
  }

  @Override
  public Type<Handler> getAssociatedType()
  {
    return TYPE;
  }

  @Override
  protected void dispatch(Handler inHandler)
  {
    inHandler.dispatch(this);
  }
}