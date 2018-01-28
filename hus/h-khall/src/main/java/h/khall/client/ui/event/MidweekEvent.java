package h.khall.client.ui.event;

import h.khall.shared.model.Page;
import h.style.g.client.ui.event.Event;
import h.style.g.client.ui.event.EventHandler;
import h.style.g.client.ui.event.TypeH;

public class MidweekEvent extends Event<MidweekEvent.Handler> implements Page
{
  public static final TypeH<Handler> TYPE = new TypeH<>(MidweekEvent.class);

  public interface Handler extends EventHandler
  {
    void dispatch(MidweekEvent inEvent);
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