package h.style.g.client.model;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.HasVisibility;

public interface TakesValueDisplay<V> extends HasVisibility, TakesValue<V>
{
  HandlerRegistration addValueChangeHandler(ValueChangeHandler<V> inHandler);
}