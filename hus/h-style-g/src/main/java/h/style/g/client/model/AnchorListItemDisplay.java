package h.style.g.client.model;

import org.gwtbootstrap3.client.ui.constants.Placement;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.IsWidget;

public interface AnchorListItemDisplay extends ButtonGroupItem, IsWidget, IconDisplay
{
  void setProperty(String inProperty);

  boolean isProperty(String inProperty);

  IsWidget getAnchor();

  boolean isSource(ClickEvent inEvent);

  void setPlacement(Placement inPlacement);

  void setActive(boolean inActive);

  void setTarget(String inTarget);
}