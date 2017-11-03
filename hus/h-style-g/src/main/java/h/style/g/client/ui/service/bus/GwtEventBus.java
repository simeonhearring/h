package h.style.g.client.ui.service.bus;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

import h.style.g.client.service.bus.EventBus;

public class GwtEventBus extends SimpleEventBus implements EventBus
{
  public GwtEventBus()
  {
    super();
  }

  @Override
  public <H extends EventHandler> HandlerRegistration addHandler(Type<H> inType, H inHandler)
  {
    return super.addHandler(inType, inHandler);
  }

  @Override
  public void fire(GwtEvent<?> inEvent)
  {
    super.fireEvent(inEvent);
  }
}