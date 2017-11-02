package h.style.g.client.model;

import java.util.List;

import org.gwtbootstrap3.client.ui.constants.ButtonSize;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.constants.Placement;
import org.gwtbootstrap3.client.ui.constants.Toggle;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.IsWidget;

public interface ButtonGroupDisplay extends ButtonGroupItem, IsWidget
{
  AnchorListItemDisplay addItem(String inText, String inTip, ClickHandler inHandler);

  AnchorListItemDisplay addItem(String inText, String inTip, String inProperty,
      ClickHandler inHandler);

  void setSize(ButtonSize inSize);

  void setType(ButtonType inType);

  void setTip(String inTitle);

  void setDropup(boolean inDropup);

  void addClickHandler(ClickHandler inHandler);

  void setPlacement(Placement inPlacement);

  void setDataToggle(Toggle inToggle);

  boolean isItemProperty(ClickEvent inEvent, String inProperty);

  List<ButtonGroupItem> getItems();

  void setSplit(boolean inSplit);

  void setNoDrop(boolean inNoDrop);

  boolean isNoDrop();

  boolean isSource(ClickEvent inEvent);

  void setClickHandler(ClickHandler inHandler);

  boolean isProperty(ClickEvent inEvent, String inProperty);

  boolean isProperty(String inProperty);

  void setProperty(String inProperty);

  String getProperty(ClickEvent inEvent);

  void setIcon(IconType inIcon);

  void addDivider();
}