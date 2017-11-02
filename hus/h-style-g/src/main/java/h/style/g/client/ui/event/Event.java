package h.style.g.client.ui.event;

import com.google.gwt.event.shared.GwtEvent;

public abstract class Event<H extends EventHandler> extends GwtEvent<H>
{
  @Override
  public String toDebugString()
  {
    return this.getClass().getSimpleName();
  }
}