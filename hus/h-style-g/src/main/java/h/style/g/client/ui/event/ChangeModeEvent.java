package h.style.g.client.ui.event;

/*
 * Hides all modes and shows selected mode.
 */
public class ChangeModeEvent extends Event<ChangeModeEvent.Handler>
{
  public interface Handler extends EventHandler
  {
    void dispatch(ChangeModeEvent inEvent);
  }

  public static final Type<Handler> TYPE = new Type<Handler>()
  {
    @Override
    public String toString()
    {
      return ChangeModeEvent.class.getSimpleName();
    }
  };

  public enum Mode
  {
    DEBUG;
  }

  private final Mode mMode;

  public ChangeModeEvent(Mode inMode)
  {
    mMode = inMode;
  }

  public Mode getMode()
  {
    return mMode;
  }

  @Override
  public Type<ChangeModeEvent.Handler> getAssociatedType()
  {
    return TYPE;
  }

  @Override
  protected void dispatch(ChangeModeEvent.Handler inHandler)
  {
    inHandler.dispatch(this);
  }

  @Override
  public String toDebugString()
  {
    return super.toDebugString() + " mode:" + mMode;
  }
}