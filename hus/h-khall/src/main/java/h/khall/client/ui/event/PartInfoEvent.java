package h.khall.client.ui.event;

import h.model.shared.khall.Part;
import h.style.g.client.ui.event.Event;
import h.style.g.client.ui.event.EventHandler;
import h.style.g.client.ui.event.TypeH;

public class PartInfoEvent extends Event<PartInfoEvent.Handler>
{
  public static final TypeH<Handler> TYPE = new TypeH<>(PartInfoEvent.class);

  public interface Handler extends EventHandler
  {
    void dispatch(PartInfoEvent inEvent);
  }

  private Part mPart;

  public PartInfoEvent(Part inPart)
  {
    mPart = inPart;
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

  public Part getPart()
  {
    return mPart;
  }
}