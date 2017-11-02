package h.style.g.client.model;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasVisibility;

public interface ButtonGroupItem extends HasText, HasVisibility, HasEnabled
{
  String getProperty();

  String getProperty(ClickEvent inEvent);
}