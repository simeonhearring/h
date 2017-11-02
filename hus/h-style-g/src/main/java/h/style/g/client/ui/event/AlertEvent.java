package h.style.g.client.ui.event;

public class AlertEvent extends Event<AlertEvent.Handler>
{
  public interface Handler extends EventHandler
  {
    void dispatch(AlertEvent inEvent);
  }

  public static final Type<Handler> TYPE = new Type<>();

  private final String mMessage;

  public AlertEvent(String inMessage)
  {
    mMessage = inMessage;
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

  public String getMessage()
  {
    return mMessage;
  }
}
