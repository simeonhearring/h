package h.style.g.client.ui.service.bus;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.Event.Type;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.SimpleEventBus;

import h.style.g.client.service.bus.EventBus;

public class GwtEventBus extends SimpleEventBus implements EventBus
{
  public GwtEventBus()
  {
    super();
  }

  @Override
  public <H> HandlerRegistration addHandler(Type<H> inType, H inHandler)
  {
    return super.addHandler(inType, inHandler);
  }

  @Override
  public void fire(Event<?> inEvent)
  {
    super.fireEvent(inEvent);
  }
}