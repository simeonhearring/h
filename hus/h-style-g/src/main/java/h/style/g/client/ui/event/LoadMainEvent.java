package h.style.g.client.ui.event;

public class LoadMainEvent extends Event<LoadMainEvent.Handler>
{
  public static final Type<Handler> TYPE = new Type<>();

  public interface Handler extends EventHandler
  {
    void dispatch(LoadMainEvent inEvent);
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