package h.style.g.client.ui.event;

public class RefreshEvent extends Event<RefreshEvent.Handler>
{
  public static final TypeH<Handler> TYPE = new TypeH<>(RefreshEvent.class);

  public interface Handler
  {
    void dispatch(RefreshEvent inEvent);
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