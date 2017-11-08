package h.style.g.client.ui.event;

public class LoadMainEvent extends Event<LoadMainEvent.Handler>
{
  public static final TypeH<Handler> TYPE = new TypeH<>(LoadMainEvent.class);

  public interface Handler
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