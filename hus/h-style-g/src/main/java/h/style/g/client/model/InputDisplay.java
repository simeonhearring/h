package h.style.g.client.model;

import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.web.bindery.event.shared.Event;

public interface InputDisplay<V> extends TakesValue<V>, IsWidget
{
  void setEnabled(boolean inEnable);

  HandlerRegistration addClickHandler(ClickHandler inHandler);

  HandlerRegistration addChangeHandler(ChangeHandler inHandler);

  HandlerRegistration addKeyUpHandler(KeyUpHandler inHandler);

  HandlerRegistration addKeyDownHandler(KeyDownHandler inHandler);

  HandlerRegistration addKeyPressHandler(KeyPressHandler inHandler);

  HandlerRegistration addFocusHandler(FocusHandler inHandler);

  HandlerRegistration addBlurHandler(BlurHandler inHandler);

  void setFocus(boolean inFocus);

  void focus(boolean inAtEnd);

  void focus(Integer inDelay, boolean inAtEnd);

  void setMaxLength(int inLength);

  boolean isSource(Event<?> inEvent);

  void setPlaceholder(String inPlaceHolder);

  void setVisible(boolean inShow);
}