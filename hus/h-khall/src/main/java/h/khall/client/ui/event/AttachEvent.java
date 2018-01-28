package h.khall.client.ui.event;

import h.style.g.client.ui.event.Event;
import h.style.g.client.ui.event.EventHandler;
import h.style.g.client.ui.event.TypeH;

public class AttachEvent extends Event<AttachEvent.Handler>
{
  public static final TypeH<Handler> TYPE = new TypeH<>(AttachEvent.class);

  public interface Handler extends EventHandler
  {
    void dispatch(AttachEvent inEvent);
  }

  public enum TypeA
  {
    LOGIN,
    LOGOUT,
    REGISTER,
    MAILBOX,
    FORGOT,
    KHALL,
  }

  private TypeA mType;

  public AttachEvent(TypeA inType)
  {
    mType = inType;
  }

  public TypeA getType()
  {
    return mType;
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