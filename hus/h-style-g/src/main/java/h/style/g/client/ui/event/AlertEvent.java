package h.style.g.client.ui.event;

public class AlertEvent extends Event<AlertEvent.Handler>
{
  public interface Handler
  {
    void dispatch(AlertEvent inEvent);
  }

  public static final TypeH<Handler> TYPE = new TypeH<>(AlertEvent.class);

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

  @Override
  public String toString()
  {
    return AlertEvent.class.getSimpleName();
  }
}
