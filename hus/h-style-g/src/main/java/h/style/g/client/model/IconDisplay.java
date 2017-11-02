package h.style.g.client.model;

import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.animate.client.ui.constants.Animation;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.web.bindery.event.shared.Event;

public interface IconDisplay extends IsWidget, HasText
{
  void clearHandlers();

  void setEnabled(boolean inEnable);

  void setShow(boolean inShow);

  void setVisible(boolean inShow);

  void setVisibility(boolean inShow);

  HandlerRegistration addClickHandler(ClickHandler inHandler);

  HandlerRegistration setClickHandler(ClickHandler inHandler);

  HandlerRegistration addDoubleClickHandler(DoubleClickHandler inHandler);

  HandlerRegistration setDoubleClickHandler(DoubleClickHandler inHandler);

  boolean isSource(Event<?> inEvent);

  boolean isShowing();

  boolean isEnabled();

  void move(String inPosition);

  void setTip(String inTip);

  void setIcon(IconType inType);

  IconType getIcon();

  void setIconTip(IconType inType, String inTip);

  void addStyleName(String inStyleName);

  void animate(Animation inAnimation, int inCount, int inDuration, int inDelay);

  void stopAnimate();

  void setIconSpin(boolean inSpin);
}