package h.khall.client.ui.event;

import h.style.g.client.ui.event.Event;
import h.style.g.client.ui.event.EventHandler;
import h.style.g.client.ui.event.TypeH;

public class AssignmentSavedEvent extends Event<AssignmentSavedEvent.Handler>
{
  public static final TypeH<Handler> TYPE = new TypeH<>(AssignmentSavedEvent.class);

  public interface Handler extends EventHandler
  {
    void dispatch(AssignmentSavedEvent inEvent);
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