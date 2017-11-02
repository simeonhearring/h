package h.style.g.client.model;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.IsWidget;

public interface LabelDisplay extends TakesValue<String>, IsWidget
{
  void setTitle(String inText);

  void setHTML(String inHtml);

  HandlerRegistration addClickHandler(ClickHandler inHandler);

  boolean isSource(ClickEvent inEvent);

  void setShow(boolean inShow);
}