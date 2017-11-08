package h.style.g.client.service.bus;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.Event.Type;
import com.google.web.bindery.event.shared.HandlerRegistration;

public interface EventBus
{
  <H> HandlerRegistration addHandler(Type<H> inType, H inHandler);

  void fire(Event<?> inEvent);
}