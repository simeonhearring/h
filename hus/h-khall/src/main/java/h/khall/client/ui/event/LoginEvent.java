package h.khall.client.ui.event;

import h.style.g.client.ui.event.Event;
import h.style.g.client.ui.event.EventHandler;

public class LoginEvent extends Event<LoginEvent.Handler>
{
  public static final Type<Handler> TYPE = new Type<>();

  public interface Handler extends EventHandler
  {
    void dispatch(LoginEvent inEvent);
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

  @Override
  public String toDebugString()
  {
    return super.toDebugString() + " login";
  }
}