package h.style.g.client.model;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.IsWidget;

public interface TabDisplay
{
  void setWidget(IsWidget inWidget);

  void addClickHandler(ClickHandler inHandler);

  void setLabel(String inLabel);

  void set(String inLabel, IsWidget inWidget);

  void setShow(boolean inShow);

  void active();
}