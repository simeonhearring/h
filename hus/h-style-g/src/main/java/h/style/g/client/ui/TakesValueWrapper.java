package h.style.g.client.ui;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.HasVisibility;

import h.style.g.client.model.TakesValueDisplay;

public class TakesValueWrapper<V, W extends HasVisibility & TakesValue<V> & HasValueChangeHandlers<V>>
    implements TakesValueDisplay<V>
{
  private W mWidget;

  public TakesValueWrapper(W inWidget)
  {
    mWidget = inWidget;
  }

  @Override
  public boolean isVisible()
  {
    return mWidget.isVisible();
  }

  @Override
  public void setVisible(boolean inVisible)
  {
    mWidget.setVisible(inVisible);
  }

  @Override
  public void setValue(V inValue)
  {
    mWidget.setValue(inValue);
  }

  @Override
  public V getValue()
  {
    return mWidget.getValue();
  }

  @Override
  public HandlerRegistration addValueChangeHandler(ValueChangeHandler<V> inHandler)
  {
    return mWidget.addValueChangeHandler(inHandler);
  }
}