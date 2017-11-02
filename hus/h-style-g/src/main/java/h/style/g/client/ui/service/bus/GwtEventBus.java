package h.style.g.client.ui.service.bus;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.SimpleEventBus;

import h.style.g.client.service.bus.EventBus;

public class GwtEventBus extends SimpleEventBus implements EventBus
{
  public GwtEventBus()
  {
    super();
  }

  @Override
  public void fire(GwtEvent<?> inEvent)
  {
    super.fireEvent(inEvent);
  }
}