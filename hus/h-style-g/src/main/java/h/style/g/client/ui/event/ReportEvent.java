package h.style.g.client.ui.event;

public class ReportEvent extends Event<ReportEvent.Handler>
{
  public static final TypeH<Handler> TYPE = new TypeH<>(ReportEvent.class);

  public interface Handler
  {
    void dispatch(ReportEvent inEvent);
  }

  private String mName;

  public ReportEvent(String inName)
  {
    mName = inName;
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

  public String getName()
  {
    return mName;
  }
}