package h.style.g.client.model;

import java.util.Date;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.web.bindery.event.shared.Event;

public interface DateSelectDisplay<H extends EventHandler> extends TakesValue<Date>, IsWidget,
    HasValueChangeHandlers<Date>
{
  void setReadOnly(boolean inReadOnly);

  void setEnabled(boolean inEnable);

  boolean isSource(Event<?> inEvent);

  void setShow(boolean inShow);

  IsWidget getDate();

  void show();

  void hide();

  HandlerRegistration addChangeDateHandler(H inHandler);
}