package h.style.g.client.service.bus;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerRegistration;

public interface EventBus
{
  <H extends EventHandler> HandlerRegistration addHandler(Type<H> inType, H inHandler);

  void fireEvent(GwtEvent<?> inEvent);

  void fire(GwtEvent<?> inEvent);
}